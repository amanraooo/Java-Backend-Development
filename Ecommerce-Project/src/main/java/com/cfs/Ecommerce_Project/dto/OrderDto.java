package com.cfs.Ecommerce_Project.dto;

import com.cfs.Ecommerce_Project.model.OrderItem;
import com.cfs.Ecommerce_Project.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

public class OrderDto {

	private Long id ;
	private String userName;
	private String email;
	private double totalAmount;
	private String status;
	private Date orderDate;

	private List<OrderItemDto> orderItems;

	public OrderDto(Long id, double totalAmount, String status, Date orderDate, String userName, String email, List<OrderItemDto> orderItems) {
		this.id = id;
		this.totalAmount = totalAmount;
		this.status = status;
		this.orderDate = orderDate;
		this.userName = userName;
		this.email = email;
		this.orderItems = orderItems;
	}
	public OrderDto(Long id, double totalAmount, String status, Date orderDate, String userName, String email) {
		this.id = id;
		this.totalAmount = totalAmount;
		this.status = status;
		this.orderDate = orderDate;
		this.userName = userName;
		this.email = email;
		this.orderItems = orderItems;
	}
	public OrderDto(Long id, double totalAmount, String status, Date orderDate, List<OrderItemDto> orderItems) {
		this.id = id;
		this.totalAmount = totalAmount;
		this.status = status;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}
}
