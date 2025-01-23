package com.cedocode.locationcar.model;

import java.time.LocalDateTime;


public class RentalDto {

    private Long vehicleId; 
    private User user;
    private Vehicle vehicle;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean chauffeur;
    private double tarifChauffeurJournee;
    private double tarifChauffeurSoiree;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) { 
        this.vehicleId = vehicleId;
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
