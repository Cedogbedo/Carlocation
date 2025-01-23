package com.cedocode.locationcar.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id") 
    private Vehicle vehicle;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean chauffeur;
    private double tarifChauffeurJournee;
    private double tarifChauffeurSoiree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        // No need for vehicleId; you can get it from the vehicle object
    }

    
    public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(boolean chauffeur) {
        this.chauffeur = chauffeur;
    }

    public double getTarifChauffeurJournee() {
        return tarifChauffeurJournee;
    }

    public void setTarifChauffeurJournee(double tarifChauffeurJournee) {
        this.tarifChauffeurJournee = tarifChauffeurJournee;
    }

    public double getTarifChauffeurSoiree() {
        return tarifChauffeurSoiree;
    }

    public void setTarifChauffeurSoiree(double tarifChauffeurSoiree) {
        this.tarifChauffeurSoiree = tarifChauffeurSoiree;
    }
}
