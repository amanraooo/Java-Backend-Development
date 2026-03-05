package com.cfd.bms.repository;

import com.cfd.bms.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByTransactionId(String transactionId);

}
