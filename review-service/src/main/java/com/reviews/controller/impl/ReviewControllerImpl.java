package com.reviews.controller.impl;

import com.reviews.controller.ReviewController;
import com.reviews.entity.Review;
import com.reviews.service.ReviewService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {

  private final ReviewService reviewService;

  @Override
  @PostMapping("/reviews")
  public ResponseEntity<Review> saveReview(@RequestBody Review review) {
    return ResponseEntity.ok(reviewService.createReview(review));
  }

  @Override
  @PutMapping("/reviews")
  @ResponseStatus(HttpStatus.OK)
  public void updateReview(@RequestBody Review review) {
    reviewService.updateReview(review.getReviewId(), review.getContent(), review.getRating());
  }

  @Override
  @DeleteMapping("/reviews/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteReview(@PathVariable("id") UUID reviewId) {
    reviewService.deleteReview(reviewId);
  }

  @Override
  @GetMapping(value = {"/reviews/{productId}","reviews/{productId}/{amount}"})
  public List<Review> getReviews(@PathVariable("productId") UUID productId, @PathVariable(value = "amount") Optional<Integer> amount){
    if(amount.isPresent()){
      return reviewService.getReviewsByProductId(productId, amount.get());
    } else {
      return reviewService.getReviewsByProductId(productId,10);
    }
  }
}
