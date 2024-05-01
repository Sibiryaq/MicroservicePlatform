package com.reviews.controller.impl;

import com.reviews.controller.ReviewController;
import com.reviews.dto.ReviewDto;
import com.reviews.service.ReviewService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {
  private final ReviewService reviewService;

  @Override
  @PostMapping("/reviews")
  @ResponseStatus(HttpStatus.OK)
  public void saveReview(@RequestBody ReviewDto reviewDto) {
    log.info("POST request to save review");
    reviewService.createReview(reviewDto);
  }

  @Override
  @PutMapping("/reviews")
  @ResponseStatus(HttpStatus.OK)
  public void updateReview(@RequestBody ReviewDto reviewDto) {
    log.info("PUT request to update review");
    reviewService.updateReview(reviewDto.reviewId(), reviewDto.content(), reviewDto.rating());
  }

  @Override
  @DeleteMapping("/reviews/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteReview(@PathVariable("id") UUID reviewId) {
    log.info("DELETE request to delete review");
    reviewService.deleteReview(reviewId);
  }

  @Override
  @GetMapping(value = {"/reviews/{productId}","reviews/{productId}/{amount}"})
  @ResponseStatus(HttpStatus.OK)
  public List<ReviewDto> getReviews(@PathVariable("productId") UUID productId,
      @PathVariable(value = "amount") Optional<Integer> amount){
    log.info("GET request to get reviews");
    if(amount.isPresent()){
      return reviewService.getReviewsByProductId(productId, amount.get());
    } else {
      return reviewService.getReviewsByProductId(productId,10);
    }
  }
}
