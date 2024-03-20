package com.hexaware.ccozyhaven.dto;

public class PaymentDTO {
	
	public Long reservationId;
	public Long userId;
	
	public String paymentMethod;
	
	public PaymentDTO() {
		super();
		
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
	
	
	

}
