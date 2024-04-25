package com.reviews.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
  @Id
  @Column(name = "review_id")
  private UUID reviewId;
  @Column(name = "user_id")
  private UUID userId;
  @Column(name = "product_id")
  private UUID productId;
  @Column(name = "content")
  private String content;
  @Column(name = "rating")
  private int rating;
  @CreationTimestamp
  @Column(name = "created_at")
  private Date date;
}
