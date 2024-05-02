package ru.aston.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.controller.OrderController;
import ru.aston.dto.OrderDto;
import ru.aston.exception.ResourceNotFoundException;
import ru.aston.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @Override
    @PutMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id,
                                                @RequestBody OrderDto orderDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDto));
    }

    @Override
    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws ResourceNotFoundException {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(orderService.getOrder(id));
    }
}