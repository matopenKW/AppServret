package com.app.form;

import java.util.ArrayList;
import java.util.List;

import com.app.dto.MinutesDto;
import com.app.dto.SyainDto;

public class EditForm extends ActionForm {

	public MinutesDto minutes = new MinutesDto();
	public List<SyainDto> sankaSyain = new ArrayList<>();
	public List<SyainDto> syainMasterList = new ArrayList<>();

}
