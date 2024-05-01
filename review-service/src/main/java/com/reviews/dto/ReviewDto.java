package com.reviews.dto;

import java.util.Date;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ReviewDto(
    UUID reviewId,
    UUID userId,
    UUID productId,
    String content,
    int rating,
    Date date) {

}
