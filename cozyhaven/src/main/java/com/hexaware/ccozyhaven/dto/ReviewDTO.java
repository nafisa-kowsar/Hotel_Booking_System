package com.hexaware.ccozyhaven.dto;

import java.util.Date;


/*
 * Author: Haswanth
 * 
 * DTO description: Represents the data transfer object for Review entity.
 * It contains fields relevant to Review, getter and setters, constructors, and relevant validations.
 */

public class ReviewDTO {
	

	
    private Long reviewId; 
    private int rating;   
    private String reviewText;    
    private Date reviewDate;

	public ReviewDTO() {
		super();
	}


	public ReviewDTO(Long reviewId, int rating, String reviewText, Date reviewDate) {
		super();
		this.reviewId = reviewId;
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


	@Override
	public String toString() {
		return "ReviewDTO [reviewId=" + reviewId + ", rating=" + rating + ", reviewText=" + reviewText + ", reviewDate="
				+ reviewDate + "]";
	}
    
    
    

}
