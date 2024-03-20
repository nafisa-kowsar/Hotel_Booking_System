package com.hexaware.ccozyhaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
