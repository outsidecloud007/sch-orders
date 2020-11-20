package com.cht.sch.orders.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private Integer id;
	
	@JsonProperty("節目")
	@Column(nullable = false)
	private String program;
	
	@JsonProperty("頻道")
	@Column(nullable = false)
	private int channel;

	@JsonProperty("客戶")
	@Column(nullable = false)
	private String client;	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}
	
	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
}