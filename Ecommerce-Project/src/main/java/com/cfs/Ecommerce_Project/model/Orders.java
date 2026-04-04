package com.cfs.Ecommerce_Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	@ManyToOne
	@JsonBackReference
	private User user;

	private double totalAmounnt;
	private String status;
	private Date orderDate;
}
