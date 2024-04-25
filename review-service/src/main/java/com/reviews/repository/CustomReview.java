package com.reviews.repository;

import java.util.List;
import java.util.UUID;

public interface CustomReview<T> {
  List<T> getReviewsLimited(UUID productId, int count);

}
