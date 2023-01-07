package com.example.RestMvcApp.services;

import com.example.RestMvcApp.models.Delivery;
import com.example.RestMvcApp.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }
}
