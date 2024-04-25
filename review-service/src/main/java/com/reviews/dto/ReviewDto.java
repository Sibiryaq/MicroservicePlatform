package com.reviews.dto;

import java.util.UUID;

public record ReviewDto(
    UUID reviewId,
    UUID userId,
    UUID productId,
    String content,
    int rating) {

}