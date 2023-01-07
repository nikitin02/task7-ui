package com.example.RestMvcApp.services;

import com.example.RestMvcApp.models.Orders;
import com.example.RestMvcApp.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }
}
