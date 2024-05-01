package com.reviews.repository;

import com.reviews.entity.Review;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID>, CustomReview<Review> {

  @Modifying
  @Query("update Review r set r.content = ?2, r.rating = ?3 where r.reviewId = ?1")
  void setReviewInfoById(UUID reviewId, String content, int rating);

  boolean existsByProductId(UUID productId);
  boolean existsByReviewIdAndUserId(UUID reviewId, UUID userId);
}
