package com.hexaware.ccozyhaven.service;

import java.util.List;

import com.hexaware.ccozyhaven.dto.ReviewDTO;
import com.hexaware.ccozyhaven.entities.Review;

import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.ReviewNotFoundException;

import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;

public interface IReviewService {

	// Add review by user Id and hotel Id
	void addReviewWithUserAndHotel(ReviewDTO reviewDTO, Long userId, Long hotelId)
			throws UserNotFoundException, HotelNotFoundException, ReservationNotFoundException;

	// Get a review by its ID
	Review getReviewById(Long reviewId) throws ReviewNotFoundException;

	// Update a review by its ID
	void updateReviewById(Long reviewId, ReviewDTO reviewDTO) throws ReviewNotFoundException;

	// Delete a review by its ID
	void deleteReviewById(Long reviewId) throws ReviewNotFoundException;

	// Get all reviews
	List<Review> getAllReviews();

	// Get all reviews for a specific hotel
	List<Review> getAllReviewsForHotel(Long hotelId) throws HotelNotFoundException;

	// Get all reviews by a specific user
	List<Review> getAllReviewsByUser(Long userId) throws UserNotFoundException;

}