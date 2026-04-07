package com.cfs.Ecommerce_Project.service;

import com.cfs.Ecommerce_Project.dto.OrderDto;
import com.cfs.Ecommerce_Project.dto.OrderItemDto;
import com.cfs.Ecommerce_Project.model.OrderItem;
import com.cfs.Ecommerce_Project.model.Orders;
import com.cfs.Ecommerce_Project.model.Product;
import com.cfs.Ecommerce_Project.model.User;
import com.cfs.Ecommerce_Project.repo.OrderRepository;
import com.cfs.Ecommerce_Project.repo.ProductRepository;
import com.cfs.Ecommerce_Project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;

	public OrderDto placeOrder(Long userId, HashMap<Long, Integer> productQuantities, double totalAmount) {
		User user = userRepository.findById(userId)
				.orElseThrow(()->new RuntimeException("user not found"));
		Orders order = new Orders();
		order.setUser(user);
		order.setOrderDate(new Date());
		order.setStatus("Pending");
		order.setTotalAmount(totalAmount);

		List<OrderItem> orderItems = new ArrayList<>();
		List<OrderItemDto> orderItemDTOS = new ArrayList<>();

		for (Map.Entry <Long, Integer> entry: productQuantities.entrySet()) {
			Product product = productRepository.findById(entry.getKey())
					.orElseThrow(()->new RuntimeException("Product Not Found"));

			OrderItem orderItem = new OrderItem();
			orderItem.setOrders(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(entry.getValue());
			orderItems.add(orderItem);
			orderItemDTOS.add(new OrderItemDto(product.getName(), product.getPrice(), entry.getValue()));
		}
		order.setOrderItem(orderItems);
		Orders saveOrder = orderRepository.save(order);
		return new OrderDto(saveOrder.getId(), saveOrder.getTotalAmounnt(),
				saveOrder.getStatus(), saveOrder.getOrderDate(), orderItemDTOS);
	}

	public List<OrderDto> getAllOrders() {
		List<Orders> orders = orderRepository.findAllOrdersWithUsers();
		return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public OrderDto convertToDTO(Orders orders) {
		List<OrderItemDto> orderItems = orders.getOrderItem().stream()
				.map(item-> new OrderItemDto(
						item.getProduct().getName(),
						item.getProduct().getPrice(),
						item.getQuantity())).collect(Collectors.toList());
		return new OrderDto(
				orders.getId(),
				orders.getTotalAmounnt(),
				orders.getStatus(),
				orders.getOrderDate(),
				orders.getUser() != null ? orders.getUser().getName() : "Unknown",
				orders.getUser() != null ? orders.getUser().getEmail() : "Unknown"
		);
	}

	public List<OrderDto> getOderByUser(Long userId) {
		Optional<User> userOp = userRepository.findById(userId);
		if (userOp.isEmpty()) {
			throw new RuntimeException("user not found");
		}

		User user = userOp.get();
		List<Orders> ordersList = orderRepository.findByUser(user);
		return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
