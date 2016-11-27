package com.shop.dao;
import java.util.ArrayList;

import com.shop.bean.ApplicationBean;

public interface Dao {
		
	public ApplicationBean doLogin(ApplicationBean appbean);
	public ArrayList<String> getCountryName();
	public ApplicationBean getPriceList(ApplicationBean appbean);
}
