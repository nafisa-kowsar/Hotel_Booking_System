package com.hexaware.ccozyhaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler({HotelOwnerNotFoundException.class})
	public ResponseEntity<String> handleExp(HotelOwnerNotFoundException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	@ExceptionHandler({InvalidCancellationException.class})
	public ResponseEntity<String> handleExp(InvalidCancellationException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({InvalidRefundException.class})
	public ResponseEntity<String> handleExp(InvalidRefundException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({RefundProcessedException.class})
	public ResponseEntity<String> handleExp(RefundProcessedException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ReservationNotFoundException.class})
	public ResponseEntity<String> handleExp(ReservationNotFoundException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ReviewNotFoundException.class})
	public ResponseEntity<String> handleExp(ReviewNotFoundException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({RoomNotAvailableException.class})
	public ResponseEntity<String> handleExp(RoomNotAvailableException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler({RoomNotFoundException.class})
	public ResponseEntity<String> handleExp(RoomNotFoundException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<String> handleExp(UserNotFoundException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({HotelNotFoundException.class})
	public ResponseEntity<String> handleExp(HotelNotFoundException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({InconsistentHotelException.class})
	public ResponseEntity<String> handleExp(InconsistentHotelException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler({DataAlreadyPresentException.class})
	public ResponseEntity<String> handleExp(DataAlreadyPresentException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	

	
	@ExceptionHandler({HotelOwnerMismatchException.class})
	public ResponseEntity<String> handleExp(HotelOwnerMismatchException e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	
	
}
