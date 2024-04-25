package com.reviews.service;

import com.reviews.entity.Review;
import java.util.List;
import java.util.UUID;

public interface ReviewService {
  Review createReview(Review review);
  void updateReview(UUID reviewId, String content, int rating);
  void deleteReview(UUID reviewId);
  List<Review> getReviewsByProductId(UUID productId, int count);
}
