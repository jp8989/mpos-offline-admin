package com.kwi.offline.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.validation.Valid;

import com.google.gson.Gson;
import com.kwi.offline.model.ErrorMessage;
import com.kwi.offline.model.GBDevice;
import com.kwi.offline.model.GBDevicesDB;
import com.kwi.offline.model.JQGridDTO;
import com.kwi.offline.model.Pushkit;
import com.kwi.offline.model.ValidationResponse;
import com.kwi.offline.model.User;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@ControllerAdvice
public class AjaxController {
	
	private static final Logger logger = Logger.getLogger(AjaxController.class);
    
	
	@RequestMapping(value="/cloudDevicePush",method=RequestMethod.GET)
	@ModelAttribute("gbDeviceList")
	public String showFormAjax(Model model,HttpServletRequest request,HttpServletResponse response) {
		logger.info("/cloudDevicePush1");
		System.out.println("/cloudDevicePush1");		
		
		//beg:original simple list of strings of deviceid
		List<String> gbdevicelist = GBDevicesDB.selectGBDevices();
		for (int i=0;i<gbdevicelist.size();i++) {
			System.out.println("gbdeviceFromcalldb:"+gbdevicelist.get(i));
		}
		
		GBDevice gbDevice = new GBDevice();
		model.addAttribute("gbDevice",gbDevice);			
		model.addAttribute("gbdevices",gbdevicelist);			
		//end:original simple list of strings of deviceid		
		
		/*
		JQGridDTO<GBDevice> gridData  = GBDevicesDB.loadGBDevicesInfo();

        Gson gson = new Gson();
		model.addAttribute("formData",gson.toJson(gridData));
        request.setAttribute("formData",gson.toJson(gridData));
		*/
		System.out.println("/cloudDevicePush2");		
	     
		return "/cloudDevicePush";
		
		 
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
        
        	
	}
	
	@RequestMapping(value="/pushkit",method= { RequestMethod.GET, RequestMethod.POST })
	public String pushkit (@ModelAttribute("pushkit") Pushkit pushkit,
							BindingResult result, ModelMap model){
		logger.info("/pushkit");
		logger.info("token:"+pushkit.getToken());
		
		RestTemplate restTemplate = new RestTemplate();
		String res = restTemplate.getForObject("http://kwidev31.kligerweiss.net:8080/mpos-offline-pushkit/restservice/offlinecontroller/pushnotify/"+pushkit.getToken(),String.class);
		//System.out.println(response.getBody());		
		
		return "/testjqgridjsp";
	}				
	
	@RequestMapping(value="/testjqgrid",method= { RequestMethod.GET, RequestMethod.POST })
	public String testJqGrid (Model model,HttpServletRequest request,HttpServletResponse response){
		Pushkit pushkit = new Pushkit();
		model.addAttribute("pushkit",pushkit);
		logger.info("/testjqgrid");
		System.out.println("/testjqgrid");		
		return "/testjqgridjsp";
	}			
	
	@RequestMapping(value="/loadjqgrid", produces="application/json" )
	public @ResponseBody JQGridDTO<GBDevice> gridData (
			@RequestParam("_search") Boolean search,
    		@RequestParam(value="filters", required=false) String filters,
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows,
    		@RequestParam(value="sidx", required=false) String sidx,
    		@RequestParam(value="sord", required=false) String sord) {			
			
	
		logger.info("/loadjqgrid");
		System.out.println("/loadjqgrid");		

		GBDevice gbDevice = new GBDevice();
		
		JQGridDTO<GBDevice> response  = GBDevicesDB.loadGBDevicesInfo();
        Gson gson = new Gson();
		return response;
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