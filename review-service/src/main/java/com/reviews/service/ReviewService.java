package com.reviews.service;

import com.reviews.dto.ReviewDto;
import java.util.List;
import java.util.UUID;

public interface ReviewService {

  /**
   * Create review and save it into DB with given DTO
   * @param reviewDto reviewDto to build into Entity
   */
  void createReview(ReviewDto reviewDto);

  /**
   * Update review content and rating
   * @param reviewId UUID of the review to update
   * @param content New content
   * @param rating New rating
   */
  void updateReview(UUID reviewId, String content, int rating);

  /**
   * Delete review by given UUID
   * @param reviewId UUID of the review
   */
  void deleteReview(UUID reviewId);

  /**
   * Gets reviews by productId, in given amount,
   * if amount is not present, returns 10 reviews
   * @param productId UUID of the product
   * @param count Amount of reviews to get
   * @return List of reviews
   */
  List<ReviewDto> getReviewsByProductId(UUID productId, int count);
}
