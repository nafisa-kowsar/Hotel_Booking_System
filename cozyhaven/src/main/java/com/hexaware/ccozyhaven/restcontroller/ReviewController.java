package com.hexaware.ccozyhaven.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ccozyhaven.dto.ReviewDTO;
import com.hexaware.ccozyhaven.entities.Review;

import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.ReviewNotFoundException;

import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;
import com.hexaware.ccozyhaven.service.IReviewService;

import jakarta.validation.Valid;
/*
 * Author: Haswanth
 * 
 * Controller description: Handles HTTP requests related to the Review entity.
 * It contains methods for registering a new Review, logging in, updating details, etc.
 */

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/review")
public class ReviewController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

	private final IReviewService reviewService;

	@Autowired
	public ReviewController(IReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping("/add/{userId}/{hotelId}")
	@PreAuthorize("hasAuthority('USER')")
	public String addReviewWithUserAndHotel(@RequestBody ReviewDTO reviewDTO, @PathVariable Long userId,
			@PathVariable Long hotelId) throws ReservationNotFoundException {
		try {
			reviewService.addReviewWithUserAndHotel(reviewDTO, userId, hotelId);
			LOGGER.info("Review added successfully");
			 return "{\"message\":\"Review added successfully\"}";
		} catch (UserNotFoundException | HotelNotFoundException e) {
			LOGGER.error("Error adding review: {}", e.getMessage());
			 return "{\"message\":\"Adding Review failed e.getMessage()\"}";
		}
	}

	@GetMapping("/get/{reviewId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
		try {
			Review review = reviewService.getReviewById(reviewId);
			LOGGER.info("Retrieved review with ID: {}", reviewId);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch (ReviewNotFoundException e) {
			LOGGER.error("Error getting review with ID {}: {}", reviewId, e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{reviewId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId,
			@RequestBody @Valid ReviewDTO reviewDTO) {
		try {
			reviewService.updateReviewById(reviewId, reviewDTO);
			LOGGER.info("Review with ID {} updated successfully", reviewId);
			return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
		} catch (ReviewNotFoundException e) {
			LOGGER.error("Error updating review with ID {}: {}", reviewId, e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{reviewId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId) {
		try {
			reviewService.deleteReviewById(reviewId);
			LOGGER.info("Review with ID {} deleted successfully", reviewId);
			return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
		} catch (ReviewNotFoundException e) {
			LOGGER.error("Error deleting review with ID {}: {}", reviewId, e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = reviewService.getAllReviews();
		LOGGER.info("Retrieved all reviews");
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/getall-by-hotel-id/{hotelId}")
	public ResponseEntity<List<Review>> getAllReviewsForHotel(@PathVariable Long hotelId) {
		try {
			List<Review> reviews = reviewService.getAllReviewsForHotel(hotelId);
			LOGGER.info("Retrieved all reviews for hotel with ID: {}", hotelId);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (HotelNotFoundException e) {
			LOGGER.error("Error getting reviews for hotel with ID {}: {}", hotelId, e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getall-by-user-id/{userId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER','ADMIN','USER')")
	public ResponseEntity<List<Review>> getAllReviewsByUser(@PathVariable Long userId) {
		try {
			List<Review> reviews = reviewService.getAllReviewsByUser(userId);
			LOGGER.info("Retrieved all reviews by user with ID: {}", userId);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			LOGGER.error("Error getting reviews by user with ID {}: {}", userId, e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}