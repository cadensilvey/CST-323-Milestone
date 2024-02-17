package com.gcu.business;

import com.gcu.model.LoginModel;

public class FiveValidLogins implements SecurityServiceInterface {
	String [][] validLogin = new String[][] {
		{"Logan","pass"},
		{"admin","admin"},
		{"test","test"},
		{"root","root"},
		{"caden","silvey"}
	};
	@Override
	public boolean isAuthenticated(LoginModel loginModel) {
		// TODO Auto-generated method stub
		
		boolean success = false;
		for(int i =0;i<validLogin.length; i++) {
			if(loginModel.getUsername().equals(validLogin[i][0]) && loginModel.getPassword().equals(validLogin[i][1])) {
				success = true;
			}
		}
		
		
		if(success) {
			return true;
		}
		else{
			return false;
		}
	}

}
