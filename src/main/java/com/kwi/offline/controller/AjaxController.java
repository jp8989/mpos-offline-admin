package com.kwi.offline.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.kwi.offline.model.ErrorMessage;
import com.kwi.offline.model.ValidationResponse;
import com.kwi.offline.model.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AjaxController {
	
    private Logger logger = Logger.getLogger(AjaxController.class);
	
	@RequestMapping(value="/cloudDevicePush",method=RequestMethod.GET)
	public String showFormAjax(Model model){
		logger.info("/cloudDevicePush");
		model.addAttribute("user", new User());
		return "/cloudDevicePush";
	}
	
	@RequestMapping(value="/offlineDeviceReports",method=RequestMethod.GET)
	public String showFormAutoComplete (Model model){
		logger.info("/offlineDeviceReports");
		model.addAttribute("user", new User());
		return "/offlineDeviceReports";
	}	
	@RequestMapping(value="/offlineDownloadSchedule",method=RequestMethod.GET)
	public String showFormJquery (Model model){
		logger.info("/offlineDownloadSchedule");
		model.addAttribute("user", new User());
		return "/offlineDownloadSchedule";
	}	
	@RequestMapping(value="/cssButton",method=RequestMethod.GET)
	public String showCssButton (Model model){
		logger.info("/cssButton");
		model.addAttribute("user", new User());
		return "/cssButton";
	}			
	
	@RequestMapping(value="/userAjax.json",method=RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(Model model, @ModelAttribute(value="user") @Valid User user, BindingResult result ){
		ValidationResponse res = new ValidationResponse();
		if(result.hasErrors()){
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getField() + "  " + objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);

		}else{
			res.setStatus("SUCCESS");
		}
		
		return res;
	}

	@RequestMapping(value="/userAjax.htm",method=RequestMethod.POST)
	public String processFormAjax(@ModelAttribute(value="user") @Valid User user, BindingResult result ){
		if(result.hasErrors()) {
			return "02-no-ajax/userForm";
		} 
		else {
			return "success";
		}
	}
}