package com.MobileServices;

import com.MobileDao.UserDao;
import com.Mobiles.User;

public class AuthService {
	private UserDao userDao = new UserDao();
	
	
	public boolean authenticate(String username, String password) {
		User user = userDao.findByUsername(username);
		if(user != null && user.getPassword().equals(password)) {
			//if authintification is succesfull then return true
			return true;
		}
		return false;
		
	}
	public String getUserRole(String username) {
		User user = userDao.findByUsername(username);
		return user != null ? user.getRole() : null;
	}	
}


