package com.hexaware.ccozyhaven.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
/*
 * Author: Haswanth
 * 
 * Entity description: contains properties related to review, getter and setters , 
 * constructors and relevant validations and mappings
*/

@Entity
@Table(name = "Review_Details")
public class Review {

	@Id
	@Column(name = "review_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence")
	@SequenceGenerator(name = "review_sequence", initialValue = 601, allocationSize = 1)
	private Long reviewId;

	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating must be at most 5")
	@Column(name = "rating")
	private int rating;

	@NotBlank(message = "Review text is required")
	@Size(max = 255, message = "Review text must be at most 255 characters")
	@Column(name = "review_text")
	private String reviewText;

	@NotNull(message = "Review date is required")
	@Column(name = "review_date")
	private Date reviewDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	@JsonBackReference
	private Hotel hotel;

	public Review() {
		super();
	}

	public Review(Long reviewId,
			@Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Rating must be at most 5") int rating,
			@NotBlank(message = "Review text is required") @Size(max = 255, message = "Review text must be at most 255 characters") String reviewText,
			@NotNull(message = "Review date is required") Date reviewDate) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.reviewText = reviewText;
		this.reviewDate = reviewDate;
	}

	public Review(
			@Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Rating must be at most 5") int rating,
			@NotBlank(message = "Review text is required") @Size(max = 255, message = "Review text must be at most 255 characters") String reviewText,
			@NotNull(message = "Review date is required") Date reviewDate) {
		super();
		this.rating = rating;
		this.reviewText = reviewText;
		this.reviewDate = reviewDate;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", rating=" + rating + ", reviewText=" + reviewText + ", reviewDate="
				+ reviewDate + "]";
	}

}