package com.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shop.bean.ApplicationBean;
import com.shop.bo.Services;



@Controller
@RequestMapping("/ShopController")
public class ShopController {
			
	@Autowired
	Services userService;
	
	@RequestMapping(value="/checklogin.htm")
	public @ResponseBody String checklogin(@RequestBody ApplicationBean appbean){
		String check = appbean.getSelectOption();
		ApplicationBean applicationBean = new ApplicationBean();
		String output = "Outputed";
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		//applicationBean = userService.doLogin(appbean);
		//output = gson.toJsonTree(applicationBean).toString();
		//obj.add("",jsonElement);
		//output = obj.toString();
		System.out.println("Inside Controller CheckedValue:> " +check);
		return check;
	}
	@RequestMapping(value="/getPriceList.htm",method=RequestMethod.POST)
	public @ResponseBody String getPriceList(@RequestBody ApplicationBean appbean){
		String output = "";
		Gson gson = new Gson();
		JsonElement json = null;
		JsonObject obj  = new JsonObject();
		ArrayList<ApplicationBean> priceList = null;
		try{
			appbean = userService.getPriceList(appbean);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		obj.addProperty("Price", appbean.getPrice());
		obj.addProperty("Tax", appbean.getTax());
		output = obj.toString();
		return output;
	}
	@RequestMapping(value="/getCountryName.htm",method=RequestMethod.GET)
	public @ResponseBody List<String> getCountryName(){
		String output = "";
		Gson gson = new Gson();
		JsonElement json = null;
		JsonObject obj  = new JsonObject();
		List<String> countryName = null;
		try{
			countryName = userService.getCountryName();
			json = gson.toJsonTree(countryName);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		obj.add("countryName", json);
		output = obj.toString();
		return countryName;
	}
	
}
