package edu.hoang.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.hoang.DAO.AccountDAO;
import edu.hoang.entity.Account;

@CrossOrigin("*")
@RestController
public class AccountRestController {
	@Autowired
	AccountDAO dao;
	@GetMapping("/rest/accounts")
	public List<Account> getAll(Model model){
		return dao.findAll();
	}
	
	@PostMapping("rest/accounts")
	public  Account post(@RequestBody Account acc) {
		dao.save(acc);
		return acc;
	}
}
