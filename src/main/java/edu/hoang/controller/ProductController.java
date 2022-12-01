package edu.hoang.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hoang.entity.Product;
import edu.hoang.service.ProductService;


@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@PreAuthorize("hasAnyRole('DIRE')") // access by "DIRECTOR"
	
	@GetMapping("/manager/product")
	public String getForm() {
		return "/manager/product";
	}

	@GetMapping("/product/list")
	public String listProduct( Model model, Optional<String> cid) throws Exception {
		if(cid.isPresent()){
			List<Product> list = productService.findAll();
			model.addAttribute("items",productService.findByCategory(cid.get()));
		}
		else {
			List<Product> list= productService.findAll();
			model.addAttribute("items",list);
		}
		return "product/list";
	}
	
	@RequestMapping("/product/home")
	public String homeProduct( Model model, Optional<String> cid) throws Exception {
		if(cid.isPresent()){
			List<Product> list = productService.findAll();
			model.addAttribute("items",productService.findByCategory(cid.get()));
		}
		else {
			List<Product> list= productService.findAll();
			model.addAttribute("items",list);
		}
		return "product/home";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detailProduct(Model model,@PathVariable("id") Integer id ) {
		Product item = productService.findById(id);
		model.addAttribute("item",item);
		return "product/detail";
	}
}
