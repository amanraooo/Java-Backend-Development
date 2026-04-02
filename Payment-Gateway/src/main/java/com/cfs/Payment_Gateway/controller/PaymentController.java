package com.cfs.Payment_Gateway.controller;

import com.cfs.Payment_Gateway.entity.PaymentOrder;
import com.cfs.Payment_Gateway.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/create-order")
	public ResponseEntity<String> createOrder(@RequestBody PaymentOrder order){
		try{
			String res = paymentService.createOrder(order);
			return ResponseEntity.ok(res);

		}catch(Exception e){
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error creating Order");
		}
	}
}
