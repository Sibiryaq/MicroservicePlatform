package com.reviews.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class CustomReviewImpl<T> implements CustomReview<T> {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<T> getReviewsLimited(UUID productId, int count) {
    return em.createNativeQuery("SELECT * FROM review WHERE product_id = ?"
            + " ORDER BY created_at DESC LIMIT ? ")
        .setParameter(1,productId)
        .setParameter(2, count)
        .getResultList();
  }
}
