package ru.aston.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long userId;
    private Double totalCost;
    private String status;
    private List<OrderItemDto> products;
}