package ru.aston.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.dto.OrderDto;
import ru.aston.dto.OrderItemDto;
import ru.aston.entity.Order;
import ru.aston.entity.OrderItem;
import ru.aston.exception.ResourceNotFoundException;
import ru.aston.repository.OrderRepository;

import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        return getOrderDto(orderDto, order);
    }

    @Transactional
    public OrderDto updateOrder(Long id, OrderDto orderDto) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ордер на найден"));
        return getOrderDto(orderDto, order);
    }



    @Transactional
    public void deleteOrder(Long id) throws ResourceNotFoundException {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ордер на найден");
        }
        orderRepository.deleteById(id);
    }

    public OrderDto getOrder(Long id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ордер на найден"));
        return convertToDto(order);
    }

    @NotNull
    private OrderDto getOrderDto(OrderDto orderDto, Order order) {
        order.setTotalCost(orderDto.getTotalCost());
        order.setStatus(orderDto.getStatus());
        order.setProducts(orderDto.getProducts().stream()
                .map(productDto -> new OrderItem(productDto.getProductId(), productDto.getQuantity()))
                .collect(Collectors.toList()));
        Order updatedOrder = orderRepository.save(order);
        return convertToDto(updatedOrder);
    }

    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserId(order.getUserId());
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setStatus(order.getStatus());
        orderDto.setProducts(order.getProducts().stream()
                .map(product -> new OrderItemDto(product.getProductId(), product.getQuantity()))
                .collect(Collectors.toList()));
        return orderDto;
    }
}