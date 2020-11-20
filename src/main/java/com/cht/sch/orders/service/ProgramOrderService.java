package com.cht.sch.orders.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cht.sch.orders.model.Order;
import com.cht.sch.orders.repository.ProgramOrderRepository;

@Service
public class ProgramOrderService {

	@Autowired
	ProgramOrderRepository repo;
	
	@Value("${schedule.programs.url}")
	String programsUrl;
	
	RestTemplate restTemplate = new RestTemplate();
	
	public List<Order> list() {
		
		return repo.findAll();
	}
	
	/**
	 * 新增節目, 限制名稱不能重疊
	 * @param program
	 * @return
	 */
	public Order add(Order order) {
		
		if (repo.findByProgramAndClient(order.getProgram(), order.getClient()) != null)
			throw new RuntimeException("The client '" + order.getClient() + "' has ordered program '" + order.getProgram() + "'!");
		
		List<Map<String, Object>> programs = restTemplate.exchange("http://" + programsUrl + "/v1/programs", HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>() {}).getBody();
		
		if (programs.stream().anyMatch(p -> p.get("節目").equals(order.getProgram())))
			return repo.save(order);
		else
			throw new RuntimeException("No such program '" + order.getProgram() + "'");
	}
	
	public void delete(Integer orderId) {
		
		repo.delete(orderId);
	}
}
