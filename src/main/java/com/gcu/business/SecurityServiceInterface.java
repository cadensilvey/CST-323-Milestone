package com.gcu.business;

import com.gcu.model.LoginModel;

public interface SecurityServiceInterface {
	public boolean isAuthenticated(LoginModel loginModel);
}
