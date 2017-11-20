package com.kwi.offline.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
//import javax.validation.Valid;

import com.kwi.offline.model.ErrorMessage;
import com.kwi.offline.model.GBDevice;
import com.kwi.offline.model.GBDevicesDB;
import com.kwi.offline.model.ValidationResponse;
import com.kwi.offline.model.User;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ControllerAdvice
public class AjaxController {
	
    private Logger logger = Logger.getAnonymousLogger();
    
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(Model model) {
		System.out.println("/cloudDevicePush0");		
        GBDevice gbDevice = new GBDevice();
        model.addAttribute("gbDevice", gbDevice);
        model.addAttribute("gbDeviceList",GBDevicesDB.selectGBDevices());
        model.addAttribute("testvar", "testvar");
        return "/cloudDevicePush";
    }
    
	
	@RequestMapping(value="/cloudDevicePush",method=RequestMethod.GET)
	@ModelAttribute("gbDeviceList")
	public String showFormAjax(Model model){
		logger.info("/cloudDevicePush");
		System.out.println("/cloudDevicePush1");		
		
		List<String> gbdevicelist = GBDevicesDB.selectGBDevices();
		
		for (int i=0;i<gbdevicelist.size();i++) {
			System.out.println("gbdeviceFromcalldb:"+gbdevicelist.get(i));
		}
		
		Map<String,GBDevice> gbdeviceinfo = GBDevicesDB.selectGBDeviceInfo();

        GBDevice gbDevice = new GBDevice();
        model.addAttribute("gbDevice", gbDevice);
        model.addAttribute("gbdevices",gbdevicelist);	
        model.addAttribute("gbdevicesinfo",gbdeviceinfo);
        
        JQGridDTO<SuperHeroDTO> gridData = new JQGridHandler().loadSuperHeroes(req);
        req.setAttribute("formData", JsonUtil.toJsonObj(gridData));
        return forward;        
 
        /*
        model.addAttribute("testvar", "testvar"); 
        List<String> coloursList = new ArrayList<String>();
                coloursList.add("IPH-B20C19A5#B9C");
                coloursList.add("IPH-AC7C6EC6A4C4");
                coloursList.add("IPH-48A38B6F88D6");
                coloursList.add("IPH-81c01ea8f7ca");
                coloursList.add("IPH-LODATOIPH123");

                model.addAttribute("colours", coloursList);
          */      
        /*
        for (int i=0;i<coloursList.size();i++) {
        	System.out.println("gbdeviceFromColoursList:"+coloursList.get(i));
        } 
        */               
        
        
		System.out.println("/cloudDevicePush2");		
	     
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
	public @ResponseBody ValidationResponse processFormAjaxJson(Model model, @ModelAttribute(value="user") User user, BindingResult result ){
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
	public String processFormAjax(@ModelAttribute(value="user") User user, BindingResult result ){
		if(result.hasErrors()) {
			return "02-no-ajax/userForm";
		} 
		else {
			return "success";
		}
	}
}