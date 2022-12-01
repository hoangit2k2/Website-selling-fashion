package edu.hoang.rest.controller;

import edu.hoang.DAO.AccountDAO;
import edu.hoang.DAO.AuthorityDAO;
import edu.hoang.DAO.RoleDAO;
import edu.hoang.entity.Authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorityRestController {
    @Autowired
    AuthorityDAO authorityDao;
    @Autowired
    RoleDAO roleDao;
    @Autowired
    AccountDAO accountDAO;
    @GetMapping("/rest/authorities")
    public Map<String,Object> getAuthorities(){
        Map<String,Object> data = new HashMap<>();
        data.put("authorities", authorityDao.findAll());
        data.put("roles",roleDao.findAll());
        data.put("accounts",accountDAO.findAll());
        return data;
    }
    @DeleteMapping("rest/authorities/{id}")
    public void delete(@PathVariable("id") Integer id){
        authorityDao.deleteById(id);
    }
    @PostMapping("/rest/authorities")
    public Authority create(@RequestBody Authority authority){
        return authorityDao.save(authority);
    }
}
