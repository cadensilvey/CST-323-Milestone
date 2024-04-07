package com.gcu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.data.OrdersDataService;
import com.gcu.model.OrderModel;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersDataService ordersDAO;

	@Autowired
	public OrdersController(OrdersDataService s){
		super();
		this.ordersDAO = s;
	}

	@GetMapping("")
	public String index(Model model){
		// List<OrderModel> orders = ordersService.getOrders();
		List<OrderModel> orders = ordersDAO.getOrders();
		
		model.addAttribute("orders",orders);
		
		return "orders";
	}

	@GetMapping("/{search}")
	public String SearchForOrder(@RequestParam(name="search", required=false) String search, Model model){

		List<OrderModel> orders = ordersDAO.searchOrders(search); 
		model.addAttribute("orders", orders);
		
		return "orders";
	}
	
	// new order 
	@GetMapping("/new")
	public String newOrder(Model model){
		model.addAttribute("order", new OrderModel());
		return "newOrder";
	}
	
	// process new order
	@PostMapping("/processNew")
	public String processNew(OrderModel order){
		ordersDAO.addOne(order);
		return "redirect:/orders/";
	}

	// edit 
	@GetMapping("/edit/{id}")
	public String editOrder(@PathVariable(value="id") Integer id, Model model){
		model.addAttribute("order", ordersDAO.getById(id));
		return "editOrder";
	}

	// process edit 
	@PostMapping("edit/processEdit")
	public String processEdit(OrderModel order){
		ordersDAO.updateOne(order.getId(), order);
		return "redirect:/orders/";
	}

	// delete 
	@GetMapping("/delete/{id}")
	public String deleteOrder(@PathVariable(value = "id")Integer id, Model model){
		ordersDAO.deleteOne(id);
		return "redirect:/orders/";

	}
}
