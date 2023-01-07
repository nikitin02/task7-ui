package com.example.RestMvcApp.repositories;

import com.example.RestMvcApp.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Integer> {
}
