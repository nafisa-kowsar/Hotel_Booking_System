package com.hexaware.ccozyhaven.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ccozyhaven.dto.PaymentDTO;
import com.hexaware.ccozyhaven.entities.Payment;
import com.hexaware.ccozyhaven.entities.Reservation;
import com.hexaware.ccozyhaven.entities.User;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;
import com.hexaware.ccozyhaven.repository.PaymentRepository;
import com.hexaware.ccozyhaven.repository.ReservationRepository;
import com.hexaware.ccozyhaven.repository.UserRepository;

import jakarta.transaction.Transactional;
@SpringBootTest
@Transactional
class PaymentServiceImpTest {
	
	
	 @Autowired
	    private IPaymentService paymentService;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private ReservationRepository reservationRepository;

	    @Autowired
	    private PaymentRepository paymentRepository;

	    @Test
	    void testProcessPayment() throws ReservationNotFoundException, UserNotFoundException {
	        // Arrange
	        User user = new User();
	        user.setUsername("testUser");
	        user.setPassword("password");
	        user.setEmail("test@example.com");
	        user.setContactNumber("1234567890");
	        user.setGender("Male");
	        user.setAddress("Test Address");
	        User savedUser = userRepository.save(user);

	        Reservation reservation = new Reservation();
	        reservation.setUser(savedUser);
	        reservation.setCheckInDate(null);
	        reservation.setCheckOutDate(null);
	        reservation.setNumberOfAdults(2);
	        reservation.setNumberOfChildren(1);
	        reservation.setTotalAmount(150.0);
	        reservation.setReservationStatus("PENDING");
	        Reservation savedReservation = reservationRepository.save(reservation);

	        PaymentDTO paymentDTO = new PaymentDTO();
	        paymentDTO.setUserId(savedUser.getUserId());
	        paymentDTO.setReservationId(savedReservation.getReservationId());
	        paymentDTO.setPaymentMethod("Credit Card");

	        // Act
	        Payment payment = paymentService.processPayment(paymentDTO.getUserId(), paymentDTO.getReservationId(), paymentDTO.getPaymentMethod());

	        // Assert
	        assertNotNull(payment.getTransactionID());
	        assertEquals("SUCCESS", payment.getStatus());
	        assertNotNull(payment.getPaymentDate());

	        Reservation updatedReservation = reservationRepository.findById(savedReservation.getReservationId()).orElse(null);
	        assertNotNull(updatedReservation);
	        assertEquals("CONFIRMED", updatedReservation.getReservationStatus());
	        assertNotNull(updatedReservation.getPayment());
	        assertEquals(payment.getAmount(), updatedReservation.getPayment().getAmount());
	        assertEquals(payment.getPaymentMethod(), updatedReservation.getPayment().getPaymentMethod());
	    }

}
