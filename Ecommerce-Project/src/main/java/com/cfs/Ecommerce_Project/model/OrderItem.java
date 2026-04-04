package com.cfs.Ecommerce_Project.model;

import jakarta.persistence.*;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;



}
