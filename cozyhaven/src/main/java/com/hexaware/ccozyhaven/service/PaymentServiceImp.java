package com.hexaware.ccozyhaven.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hexaware.ccozyhaven.entities.Payment;
import com.hexaware.ccozyhaven.entities.Reservation;
import com.hexaware.ccozyhaven.entities.User;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;
import com.hexaware.ccozyhaven.repository.PaymentRepository;
import com.hexaware.ccozyhaven.repository.ReservationRepository;
import com.hexaware.ccozyhaven.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImp implements IPaymentService{
	
	private final PaymentRepository paymentRepository;
	private final UserRepository userRepository;
	private final ReservationRepository reservationRepository;
	
    @Autowired
    public PaymentServiceImp(PaymentRepository paymentRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }
   
    
    
    @Override
    public Payment processPayment( Long userId, Long reservationId, String paymentMethod) throws ReservationNotFoundException, UserNotFoundException {
       
    	 User user = userRepository.findById(userId)
 	            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

 	    Reservation reservation = reservationRepository.findById(reservationId)
 	            .orElseThrow(() -> new ReservationNotFoundException("Cart not found with id: " + reservationId));

 	   reservation.setReservationStatus("CONFIRMED");
 	    Payment payment = new Payment();
 	    payment.setUser(user);
 	    payment.setReservation(reservation);
 	    payment.setPaymentDate(LocalDateTime.now());
 	    payment.setAmount(reservation.getTotalAmount());
 	    payment.setPaymentMethod(paymentMethod);
 	    payment.setTransactionID(generateRandomTransactionID());
 	    payment.setStatus("SUCCESS");
 	    
 	    reservation.setReservationStatus("CONFIRMED");
 	    reservation.setPayment(payment);
        
        return paymentRepository.save(payment);
    }
    
    private String generateRandomTransactionID() {
        Random random = new Random();
        int transactionIDLength = 10;
        StringBuilder sb = new StringBuilder(transactionIDLength);
        for (int i = 0; i < transactionIDLength; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
