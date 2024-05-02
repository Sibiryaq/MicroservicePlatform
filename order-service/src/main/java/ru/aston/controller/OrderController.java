package ru.aston.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.OrderDto;
import ru.aston.exception.ResourceNotFoundException;


@Tag(name = "Order controller",
        description = "Controller to execute CRUD operations with orders")
public interface OrderController {
    @PostMapping("/orders")
    @Operation(
            summary = "Create order",
            description = "Creates a new order in the db"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order successfully created"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto);

    @PutMapping("/orders/{id}")
    @Operation(
            summary = "Update order information",
            description = "Updates an existing order"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order successfully updated"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Order not found"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) throws ResourceNotFoundException;

    @DeleteMapping("/orders/{id}")
    @Operation(
            summary = "Delete order",
            description = "Deletes an order from the database by its id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Order not found"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws ResourceNotFoundException;

    @GetMapping("/orders/{id}")
    @Operation(
            summary = "Get order",
            description = "Get an order by its id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Got the order from the database"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    ResponseEntity<OrderDto> getOrder(@PathVariable Long id) throws ResourceNotFoundException;
}