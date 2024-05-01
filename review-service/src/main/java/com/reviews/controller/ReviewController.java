package com.reviews.controller;

import com.reviews.dto.ReviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Review controller",
     description = "Controller to execute CRUD operations with reviews")
public interface ReviewController {
  @PostMapping("/reviews")
  @Operation(
      summary = "Save review",
      description = "Saves review into the db"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Review successfully added"
          ),
          @ApiResponse(
              responseCode = "409",
              description = "Review with this UUID already exists"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error"
          )
      }
  )
  void saveReview(@RequestBody ReviewDto reviewDto);

  @PutMapping("/reviews")
  @Operation(
      summary = "Update review information",
      description = "Updates content/rating of the review"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Review successfully updated"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Review not found"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error"
          )
      }
  )
  void updateReview(@RequestBody ReviewDto reviewDto);

  @DeleteMapping("/reviews/{id}")
  @Operation(
      summary = "Delete review",
      description = "Deletes review from database by its id"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Review successfully deleted"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Review not found"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error"
          )
      }
  )
  void deleteReview(@PathVariable("id") UUID reviewId);

  @DeleteMapping("/reviews/{id}")
  @Operation(
      summary = "Get reviews",
      description = "Get fixed amount of reviews, (10 by default)"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Got some reviews from database"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error"
          )
      }
  )
  List<ReviewDto> getReviews(@PathVariable("productId") UUID productId, @PathVariable(value = "amount") Optional<Integer> amount);
}
