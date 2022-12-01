package edu.hoang.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.hoang.entity.Order;
import net.bytebuddy.asm.Advice.Return;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	List<Order> findByUsername(String username);

}
