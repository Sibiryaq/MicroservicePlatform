package ru.aston.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long userId;
    private Double totalCost;
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> products;

}
