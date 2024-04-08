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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersDataService ordersDAO;

	Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	public OrdersController(OrdersDataService s){
		super();
		this.ordersDAO = s;
	}


	@GetMapping("/")
public String index(Model model) {
    // Log entry
    logger.info("Entering index method");

    List<OrderModel> orders = ordersDAO.getOrders();
    model.addAttribute("orders", orders);

    // Statement that we got the orders
    logger.info("Retrieved orders successfully");

    // Log exit
    logger.info("Exiting index method");
    
    return "orders";
}

@GetMapping("/{search}")
public String searchForOrder(@RequestParam(name="search", required=false) String search, Model model) {
    // Log entry
    logger.info("Entering searchForOrder method");

    List<OrderModel> orders = ordersDAO.searchOrders(search); 
    model.addAttribute("orders", orders);

    // Log that we searched for orders
    logger.info("Searched the orders successfully");

    // Log exit
    logger.info("Exiting searchForOrder method");
    
    return "orders";
}

@GetMapping("/new")
public String newOrder(Model model) {
    // Log entry
    logger.info("Entering newOrder method");

    model.addAttribute("order", new OrderModel());

    // Log that a new order is made 
    logger.info("A new order is created");

    // Log exit
    logger.info("Exiting newOrder method");
    
    return "newOrder";
}

@PostMapping("/processNew")
public String processNew(OrderModel order) {
    // Log entry
    logger.info("Entering processNew method");

    ordersDAO.addOne(order);

    // Log exit
    logger.info("Exiting processNew method");
    
    return "redirect:/orders/";
}

@GetMapping("/edit/{id}")
public String editOrder(@PathVariable(value="id") Integer id, Model model) {
    // Log entry
    logger.info("Entering editOrder method");

    model.addAttribute("order", ordersDAO.getById(id));

    // Log exit
    logger.info("Exiting editOrder method");
    
    return "editOrder";
}

@PostMapping("edit/processEdit")
public String processEdit(OrderModel order) {
    // Log entry
    logger.info("Entering processEdit method");

    ordersDAO.updateOne(order.getId(), order);

    // Log exit
    logger.info("Exiting processEdit method");
    
    return "redirect:/orders/";
}

@GetMapping("/delete/{id}")
public String deleteOrder(@PathVariable(value = "id") Integer id, Model model) {
    // Log entry
    logger.info("Entering deleteOrder method");

    ordersDAO.deleteOne(id);

    // Log that an order is deleted
    logger.info("Deleted an order successfully");

    // Log exit
    logger.info("Exiting deleteOrder method");
    
    return "redirect:/orders/";
}

}
