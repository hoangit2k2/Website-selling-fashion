package edu.hoang.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hoang.DAO.CategoryDAO;
import edu.hoang.DAO.ProductDAO;
import edu.hoang.entity.Product;

@CrossOrigin("*") // response header Access-EntityControl-Allow-Origin
@RestController
public class ProductRestController {
	@Autowired
	private ProductDAO productService;
	@Autowired
	private CategoryDAO categoryService;

	// GET -> ALL TO MAP
	@RequestMapping({ "/rest/products" })
	public ResponseEntity<Map<String, List>> getMap(Model model) {
		Map<String, List> map = new HashMap<>();
		map.put("categories", categoryService.findAll());
		map.put("products", productService.findAll());
		return ResponseEntity.ok(map);
	}
	
	// GET -> READ WITH ID
	@GetMapping("/rest/products/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Integer id) {
		Optional<Product> optional = productService.findById(id);
		return optional.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(optional.get());
	}
	
	// POST -> SAVE
	@PostMapping("/rest/products")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		try {
			return ResponseEntity.ok(productService.save(product));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	// PUT -> UPDATE
	@PutMapping("/rest/products")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		Optional<Product> optional = productService.findById(product.getId());
		try {
			if (!optional.isEmpty()) return ResponseEntity.ok(productService.save(product));
		} catch (Exception e) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	// DELETE -> DELETE BY ID
	@DeleteMapping("/rest/products/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Product> optional = productService.findById(id);
		if (optional.isEmpty())
			return ResponseEntity.notFound().build();
		else
			productService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
