package com.cedocode.locationcar.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedocode.locationcar.model.Vehicle;



@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
