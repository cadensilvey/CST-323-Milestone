package com.gcu;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;

import com.gcu.business.FiveValidLogins;
import com.gcu.business.OrdersBusinessService;
import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.RegistrationBusinessService;
import com.gcu.business.RegistrationBusinessServiceInterface;
import com.gcu.business.SecurityServiceInterface;
import com.gcu.data.OrdersDataService;
import com.gcu.data.OrdersDataAccessInterface;
import com.gcu.model.OrderModel;

@Configuration
public class SpringConfig {
	
	@Bean(name="OrdersBusinessService", initMethod = "init", destroyMethod = "destroy")
	@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public OrdersBusinessServiceInterface getOrdersBusiness() {
		return new OrdersBusinessService();
	}

	@Bean(name="RegistrationBusinessService", initMethod = "init", destroyMethod = "destroy")
	@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public RegistrationBusinessServiceInterface getRegistrationBusiness() {
		return new RegistrationBusinessService();
	}

	
	@Autowired
	private DataSource dataSource;
	
	@Bean(name="ordersDAO")
	@RequestScope
	public OrdersDataAccessInterface<OrderModel> getDataService(){
		return new OrdersDataService(dataSource);
	}
	
	@Bean(name="securityService")
		public SecurityServiceInterface getSecurityService() {
			return new FiveValidLogins();
		}
	
}
