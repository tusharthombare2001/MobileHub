package com.MobileServices;



import java.util.List;

import com.MobileDao.MobileDao;
import com.Mobiles.Mobile;



public class MobileServices {
	private MobileDao mobileDao = new MobileDao();

	public String addMobile(int id, String mobileName, int mobileQuantity, int mobileCost) {
		Mobile mobile = new Mobile();
		mobile.setId(id);
		mobile.setMobileName(mobileName);
		mobile.setMobileQuantity(mobileQuantity);
		mobile.setMobileCost(mobileCost);

		mobileDao.saveMobileDetails(mobile);
		return "Mobile added succesfully";
	}

	public String deleteMobile(int id) {
		return mobileDao.deleteMobileById(id);
	}

	public List<Mobile> getAllMobileData() {
		return mobileDao.getAllMobileData();
	}
	
	public Mobile FetchMobileById(int id) {
		return mobileDao.getMobileById(id);
	}
	
	public String updateMobileName(int id, String newName) {
		return mobileDao.updateMobileName(id, newName);
	}
	
	  public String updateMobileQuantity(int id, int newQuantity) {
	        return mobileDao.updateMobileQuantity(id, newQuantity);
	    }

	    public String updateMobileCost(int id, int newCost) {
	        return mobileDao.updateMobileCost(id, newCost);
	    }
	    
	    public List<Mobile> getMobilileByCostRange(int minCost, int maxCost) {
			return mobileDao.getMobilileByCostRange(minCost, maxCost);
		}
	    
	    public List<Mobile> getMobileByBrandName(String brandName){
	    	return mobileDao.getMobileByBrandName(brandName);
	    }

}

