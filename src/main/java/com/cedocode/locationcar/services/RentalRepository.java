package com.cedocode.locationcar.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedocode.locationcar.model.Rental;
import com.cedocode.locationcar.model.User;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

	 List<Rental> findByUser(User user, Sort sort);

}