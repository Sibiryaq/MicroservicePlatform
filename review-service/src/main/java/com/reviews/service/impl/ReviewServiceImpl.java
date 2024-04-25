package com.reviews.service.impl;

import com.reviews.entity.Review;
import com.reviews.repository.ReviewRepository;
import com.reviews.service.ReviewService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Override
  public Review createReview(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public void updateReview(UUID reviewId, String content, int rating) {
    reviewRepository.setReviewInfoById(reviewId,content,rating);
  }

  @Override
  public void deleteReview(UUID reviewId) {
    reviewRepository.deleteById(reviewId);
  }

  @Override
  public List<Review> getReviewsByProductId(UUID productId, int count) {
    return reviewRepository.getReviewsLimited(productId,count);
  }
}
