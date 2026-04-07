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
	private double totalAmounnt;
	private String status;
	private Date orderDate;

	private List<OrderItemDto> orderItems;

}
