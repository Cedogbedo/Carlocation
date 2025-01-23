package com.cedocode.locationcar.servicesss;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cedocode.locationcar.model.Rental;
import com.cedocode.locationcar.model.User;
import com.cedocode.locationcar.services.RentalRepository;


@Service
public class RentalServicess {

    private static final double DAYTIME_RATE = 5000;
    private static final double EVENING_RATE = 10000;

    public double calculateTotalFees(Rental rental) {
        if (rental.getDateFin().isBefore(rental.getDateDebut())) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        long durationInHours = calculateRentalDurationInHours(rental.getDateDebut(), rental.getDateFin());
        double hourlyRate = rental.getVehicle().getTarifHoraire();
        double vehicleRentalFees = durationInHours * hourlyRate;

        double chauffeurFees = 0;
        if (rental.isChauffeur()) {
            chauffeurFees = calculateChauffeurFees(rental.getDateDebut(), rental.getDateFin());
        }

        return vehicleRentalFees + chauffeurFees;
    }

    private long calculateRentalDurationInHours(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long hours = duration.toHours();
        // Ajouter une heure si des minutes sont présentes
        if (duration.toMinutes() % 60 > 0) {
            hours += 1;
        }
        return hours;
    }

    private double calculateChauffeurFees(LocalDateTime start, LocalDateTime end) {
        long daytimeHours = 0;
        long eveningHours = 0;

        // Définir les heures de début et de fin de la journée
        LocalTime eveningStart = LocalTime.of(18, 0); // 18h du soir

        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        if (startTime.isBefore(eveningStart)) {
            if (endTime.isBefore(eveningStart)) {
                daytimeHours = Duration.between(start, end).toHours();
            } else {
                daytimeHours = Duration.between(start, eveningStart.atDate(start.toLocalDate())).toHours();
                eveningHours = Duration.between(eveningStart.atDate(start.toLocalDate()), end).toHours();
            }
        } else {
            eveningHours = Duration.between(start, end).toHours();
        }

        return (daytimeHours * DAYTIME_RATE) + (eveningHours * EVENING_RATE);
    }
    
    @Autowired
    private RentalRepository rentalRepository;


    public Rental findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return rentalRepository.findById(id).orElse(null); 
    }
    
    @Autowired
    private RentalRepository rentalRepo;

    public List<Rental> findByUser(User user, Sort sort) {
        return rentalRepo.findByUser(user, sort);
    }
    
    
    
}
