package com.hexaware.ccozyhaven.restcontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.hexaware.ccozyhaven.entities.Payment;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;

import com.hexaware.ccozyhaven.service.IPaymentService;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	 private final IPaymentService paymentService;
	  

	    @Autowired
	    public PaymentController(IPaymentService paymentService) {
	        this.paymentService = paymentService;
	        
	    }

	    @PostMapping("/make-payment/{reservationId}")
	    @PreAuthorize("hasAuthority('USER')")
	    public ResponseEntity<Payment> processPayment(@PathVariable Long reservationId, @RequestParam("userId") Long userId, @RequestParam("paymentMethod") String paymentMethod)
	            throws ReservationNotFoundException, UserNotFoundException {
	       
	        Payment processedPayment = paymentService.processPayment(userId,reservationId, paymentMethod);

	        return new ResponseEntity<>(processedPayment, HttpStatus.OK);
	    }

}
