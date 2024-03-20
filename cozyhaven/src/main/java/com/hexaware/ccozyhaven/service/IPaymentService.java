package com.hexaware.ccozyhaven.service;


import com.hexaware.ccozyhaven.entities.Payment;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;

public interface IPaymentService {
	
	public Payment processPayment( Long userId, Long reservationId, String paymentMethod) throws ReservationNotFoundException, UserNotFoundException;
	
	

}
