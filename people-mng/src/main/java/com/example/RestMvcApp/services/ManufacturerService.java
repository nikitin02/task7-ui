package com.example.RestMvcApp.services;

import com.example.RestMvcApp.models.Manufacturer;
import com.example.RestMvcApp.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }
}
