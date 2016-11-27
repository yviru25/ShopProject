package com.shop.boImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.bean.ApplicationBean;
import com.shop.bo.Services;
import com.shop.dao.Dao;

public class ServicesImpl implements Services {
	
	@Autowired
	Dao userDao;
	
	@Override
	public ApplicationBean doLogin(ApplicationBean appbean) {
		return userDao.doLogin(appbean);
	}

	@Override
	public ApplicationBean getPriceList(ApplicationBean appbean) {
		return userDao.getPriceList(appbean);
	}
	public ArrayList<String> getCountryName(){
		return userDao.getCountryName();
	}

}
