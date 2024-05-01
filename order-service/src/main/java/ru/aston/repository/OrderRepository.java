package ru.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrder<Order> {
}


/*
OrderRepository наследует JpaRepository,
дает доступ ко многим встроенным методам
для работы с базой данных, таким как
save(), findById(), findAll(), count(), deleteById() и т.д
 */