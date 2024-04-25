package com.reviews.controller;

import com.reviews.entity.Review;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReviewController {
  @PostMapping
  ResponseEntity<Review> saveReview(@RequestBody Review review);

  @PutMapping
  void updateReview(@RequestBody Review review);

  @DeleteMapping
  void deleteReview(@PathVariable("id") UUID reviewId);

  @GetMapping
  List<Review> getReviews(@PathVariable("productId") UUID productId, @PathVariable(value = "amount") Optional<Integer> amount);

}
