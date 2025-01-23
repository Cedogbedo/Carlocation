package com.cedocode.locationcar.servicesss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cedocode.locationcar.model.Vehicle;
import com.cedocode.locationcar.services.VehicleRepository;


@Service
public class VehicleServiceImpl implements VehicleServices {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return vehicleRepository.findById(id).orElse(null); 
    }
}
