package com.cfs.Payment_Gateway.service;

import com.cfs.Payment_Gateway.entity.PaymentOrder;
import com.cfs.Payment_Gateway.repo.PaymentRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {


	@Value("${razorpay.key_id}")
	private String keyId;

	@Value("${razorpay.key_secret}")
	private String keySecret;

	@Autowired
	private PaymentRepo paymentRepo;

	public String createOrder(PaymentOrder orderDetails) throws RazorpayException{
		RazorpayClient client = new RazorpayClient(keyId, keySecret);

		//json
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", orderDetails.getAmount()*100);
		orderRequest.put("Currency", "INR");
		orderRequest.put("reciept","txn_"+ UUID.randomUUID());

		Order razorpayOrder = client.orders.create(orderRequest);

		System.out.println(razorpayOrder.toString());

		orderDetails.setOrderId(razorpayOrder.get("id"));
		orderDetails.setStatus("CREATED");
		orderDetails.setCreatedAt(LocalDateTime.now());

		paymentRepo.save(orderDetails);
		return razorpayOrder.toString();
	}
}
