package com.reviews.service.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.reviews.entity.Review;
import com.reviews.repository.ReviewRepository;
import com.reviews.service.ReviewService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    boolean answer = reviewRepository.existsById(reviewId);
    if(answer){
      reviewRepository.deleteById(reviewId);
    } else {
      throw new ResponseStatusException(NOT_FOUND, "Such review does not exist");
    }
    reviewRepository.setReviewInfoById(reviewId,content,rating);
  }

  @Override
  public void deleteReview(UUID reviewId) {
    boolean answer = reviewRepository.existsById(reviewId);
    if(answer){
      reviewRepository.deleteById(reviewId);
    } else {
      throw new ResponseStatusException(NOT_FOUND, "Such review does not exist");
    }
  }

  @Override
  public List<Review> getReviewsByProductId(UUID productId, int count) {
    boolean answer = reviewRepository.existsByProductId(productId);
    if(answer) {
      return reviewRepository.getReviewsLimited(productId, count);
    } else {
      throw new ResponseStatusException(NOT_FOUND, "No reviews for such product");
    }
  }
}
