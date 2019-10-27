package com.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dto.MinutesDto;
import com.app.exception.MinutesException;
import com.app.form.ActionForm;
import com.app.form.MinutesListForm;
import com.app.logic.MinutesListLogic;

public class MinutesListAction extends AbstractAction{

	public MinutesListAction(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public ActionForm execute() throws MinutesException{
		MinutesListForm form = new MinutesListForm();
		form.minutesList = getMinutesList();
		return form;
	}

	private List<MinutesDto> getMinutesList() throws MinutesException {
		List<MinutesDto> list = new ArrayList<MinutesDto>();
		list = new MinutesListLogic(getConnection()).select();
		return list;
	}
}