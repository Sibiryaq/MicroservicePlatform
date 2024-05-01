package ru.aston.repository;

import java.util.List;

public interface CustomOrder<T> {
    List<T> getOrdersByUserId(Long userId);
}
