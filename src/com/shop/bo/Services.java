package com.shop.bo;

import java.util.ArrayList;

import com.shop.bean.ApplicationBean;

public interface Services {
		
		public ApplicationBean doLogin(ApplicationBean appbean);
		public ArrayList<String> getCountryName();
		public ApplicationBean getPriceList(ApplicationBean appbean);
}
