package com.shop.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.shop.bean.ApplicationBean;
import com.shop.dao.Dao;

import oracle.jdbc.driver.OracleTypes;

public class DaoImpl implements Dao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public ApplicationBean doLogin(ApplicationBean appbean) {
		String output="";
		Gson gson = new Gson();
		ApplicationBean appOut = new ApplicationBean();
		try{
			
			CallableStatement cs = null;
			Connection conn = this.dataSource.getConnection();
			cs = conn.prepareCall("BEGIN proc_user_login_master(?,?,?,?,?,?); END;");
			cs.setString(1, appbean.getUserLogon());
			cs.setString(2, appbean.getPassword());
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.registerOutParameter(4, OracleTypes.VARCHAR);
			cs.registerOutParameter(5, OracleTypes.VARCHAR);
			cs.registerOutParameter(6, OracleTypes.VARCHAR);
			cs.execute();
			appOut.setUserId(cs.getInt(3));
			appOut.setUserName(cs.getString(4));
			appOut.setAppAccess(cs.getString(5));
			appOut.setStatus(cs.getString(6));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return appOut;
	}

	@Override
	public ApplicationBean getPriceList(ApplicationBean appbean) {
		ArrayList<ApplicationBean> stateArray = new ArrayList<ApplicationBean>();
		ApplicationBean appbeanOut=new ApplicationBean();
		try{			
			String query = "select l.price,l.tax from productName p,priceList l where p.product_id=l.product_id and trim(p.productnames)=trim(?)";
			PreparedStatement pst = this.dataSource.getConnection().prepareStatement(query);
			pst.setString(1, appbean.getProductNames());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				
				appbeanOut.setPrice(rs.getDouble("price"));
				appbeanOut.setTax(rs.getDouble("tax"));
				stateArray.add(appbeanOut);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return appbeanOut;
	}

	@Override
	public ArrayList<String> getCountryName() {
		ArrayList<String> countryArray = new ArrayList<String>();
		try{
			String query = "select productnames from productName";
			PreparedStatement pst = this.dataSource.getConnection().prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String citynames = rs.getString("productnames");
				countryArray.add(citynames);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return countryArray;
	}

}
