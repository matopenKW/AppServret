package com.app.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.app.dto.MinutesDto;
import com.app.dto.SyainDto;
import com.app.exception.MinutesException;
import com.app.form.ActionForm;
import com.app.form.EditForm;
import com.app.logic.EditLogic;

public class EditAction extends AbstractAction{

	public EditAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public ActionForm execute() throws MinutesException{
		String mode = request.getParameter("mode");
		EditForm form;

		String targetSeqno = request.getParameter("targetSeqno");
		int seqno;
		if (StringUtils.isBlank(targetSeqno)) {
			seqno = 0;
		} else {
			seqno = Integer.parseInt(targetSeqno);
		}

		if ("SELECT".equals(mode)) {
			form = select(seqno);
		} else if("UPDATE".equals(mode)) {
			form = update(seqno);
		} else if("DELETE".equals(mode)) {
			form = delete(seqno);
		} else {
			throw new MinutesException("えらー");
		}
		return form;
	}

	private EditForm select(int seqno) throws MinutesException {

		EditForm form = new EditForm();
		EditLogic logic = new EditLogic(getConnection());

		// 新規ではない場合はDBから取得
		if (seqno != 0) {
			form.minutes = logic.select(seqno);
			form.sankaSyain = logic.selectSyainList(seqno);
		}
		form.syainMasterList = logic.selectSyainMasterList();
		return form;
	}

	private EditForm update(int targetSeqno) throws MinutesException {

		EditLogic logic = new EditLogic(getConnection());

		MinutesDto dto = getParam(targetSeqno);
		ArrayList<SyainDto> syainList = getParamSyain(targetSeqno);
		if (dto.getSeqno() != 0) {
			logic.update(dto);

			// 社員はdelete→insert
			logic.deleteSyain(targetSeqno);
			for (SyainDto syainDto : syainList) {
				logic.insertSyain(targetSeqno, syainDto);
			}

		} else {
			dto.setSeqno(logic.selectMaxSeqno());
			logic.insert(dto);

			for (SyainDto syainDto : syainList) {
				logic.insertSyain(dto.getSeqno(), syainDto);
			}
		}

		return select(dto.getSeqno());
	}

	private EditForm delete(int targetSeqno) throws MinutesException {
		EditLogic logic = new EditLogic(getConnection());

		logic.delete(targetSeqno);
		logic.deleteSyain(targetSeqno);

		// 削除後は新規画面として表示
		request.setAttribute("targetSeqno", 0);
		return select(targetSeqno);
	}

	private MinutesDto getParam(int seqno) {
		MinutesDto dto = new MinutesDto();

		String title = request.getParameter("title");
		String dateTime = request.getParameter("date-time");
		String place = request.getParameter("place");
		String content = request.getParameter("content");

		dto.setSeqno(seqno);
		dto.setName(title);
		dto.setDateTime(dateTime);
		dto.setPlace(place);
		dto.setContent(content);
		return dto;
	}

	private ArrayList<SyainDto> getParamSyain(int targetSeqno) {

		String[] syainCodeList = request.getParameterValues("syainCode");

		ArrayList<SyainDto> list = new ArrayList<SyainDto>();
		if (syainCodeList != null && syainCodeList.length > 0) {
			for (String syainCode : syainCodeList) {
				SyainDto dto = new SyainDto();
				dto.setCode(syainCode);
				list.add(dto);
			}
		}
		return list;
	}
}