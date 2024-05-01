package ru.aston.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomOrderImpl<T> implements CustomOrder<Object> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object> getOrdersByUserId(Long userId) {
        return em.createNativeQuery("SELECT * FROM order WHERE user_id = ?")
                .setParameter(1, userId)
                .getResultList();
    }
}

