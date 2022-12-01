package edu.hoang.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hoang.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderservice;
	
	@RequestMapping("/order/checkout")
	public String Checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String List(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders",orderservice.findByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String Detail(@PathVariable("id")Long id,Model model) {
		model.addAttribute("order",orderservice.findById(id));
		return "order/detail";
	}
}
