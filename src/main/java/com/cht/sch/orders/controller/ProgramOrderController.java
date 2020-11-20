package com.cht.sch.orders.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cht.sch.orders.model.Order;
import com.cht.sch.orders.service.ProgramOrderService;

@RestController
@RequestMapping("/v1/orders")
public class ProgramOrderController {

	@Autowired
	ProgramOrderService svc;
	
	@GetMapping
	List<Order> list() {
		
		return svc.list();
	}
		
	@PostMapping
	Order add(HttpServletRequest request, @RequestBody Order order) {
		
		String client = request.getHeader("X-CLIENT-Id");
		if (client != null)
			order.setClient(client);
		return svc.add(order);
	}
	
	@DeleteMapping("/{orderId}")
	void delete(@PathVariable Integer orderId) {
		
		svc.delete(orderId);
	}

}
