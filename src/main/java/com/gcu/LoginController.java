 package com.gcu;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.SecurityServiceInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	SecurityServiceInterface securityService;
	
	@GetMapping("/")
	public String display(Model model) {
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());

		return "login";
	}

	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, OrderModel orderModel, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}

		if (securityService.isAuthenticated(loginModel)) {

			// model.addAttribute("model", loginModel);
			// model.addAttribute("model", orderModel);
			// OrdersController oc = new OrdersController();
			// model.addAttribute("title", "Products: ");
			// model.addAttribute("orders", oc.index(model));
			// return "orders";
			// model.addAttribute("model", loginModel);
			// List<OrderModel> orders = ordersService.getOrders();
			// model.addAttribute("title", "Products:");
			// model.addAttribute("orders", orders);
			// return "orders";
			return "redirect:/orders/";

		}

		else {
			return "login";
		}

	}
}