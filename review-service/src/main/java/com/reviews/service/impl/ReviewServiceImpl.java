package com.reviews.service.impl;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.reviews.dto.ReviewDto;
import com.reviews.entity.Review;
import com.reviews.repository.ReviewRepository;
import com.reviews.service.ReviewService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Override
  public void createReview(ReviewDto reviewDto) {
    boolean answer = reviewRepository.existsById(reviewDto.reviewId());
    if(answer){
      throw new ResponseStatusException(CONFLICT, "Review with this UUID already exists");
    }
    answer = reviewRepository.existsByReviewIdAndUserId(
        reviewDto.reviewId(),
        reviewDto.userId());
    if(answer) {
      throw new ResponseStatusException(CONFLICT, "Only one review per product allowed");
    }
    Review review = new Review(
        reviewDto.reviewId(),
        reviewDto.userId(),
        reviewDto.productId(),
        reviewDto.content(),
        reviewDto.rating(),
        reviewDto.date()
    );
    reviewRepository.save(review);
    log.info("Saved review to DB");
  }

  @Override
  public void updateReview(UUID reviewId, String content, int rating) {
    boolean answer = reviewRepository.existsById(reviewId);
    if(answer){
      reviewRepository.setReviewInfoById(reviewId,content,rating);
      log.info("Updated review by UUID = {}", reviewId);
    } else {
      throw new ResponseStatusException(NOT_FOUND, "Such review does not exist");
    }
  }

  @Override
  public void deleteReview(UUID reviewId) {
    boolean answer = reviewRepository.existsById(reviewId);
    if(answer){
      reviewRepository.deleteById(reviewId);
      log.info("Deleted review by UUID = {}", reviewId);
    } else {
      throw new ResponseStatusException(NOT_FOUND, "Such review does not exist");
    }
  }

  @Override
  public List<ReviewDto> getReviewsByProductId(UUID productId, int count) {
    boolean answer = reviewRepository.existsByProductId(productId);
    if(answer) {
      return reviewRepository.getReviewsLimited(productId, count).stream()
          .map(this::convertReviewToReviewDto)
          .toList();
    } else {
      throw new ResponseStatusException(NOT_FOUND, "No reviews for such product");
    }
  }

  private ReviewDto convertReviewToReviewDto(Review review){
    return ReviewDto.builder()
        .reviewId(review.getReviewId())
        .userId(review.getUserId())
        .productId(review.getProductId())
        .content(review.getContent())
        .rating(review.getRating())
        .date(review.getDate())
        .build();
  }
}
