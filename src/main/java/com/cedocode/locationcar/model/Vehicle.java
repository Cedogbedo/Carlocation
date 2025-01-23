package com.cedocode.locationcar.model;
import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table (name = "vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id;
    private String marque;
    private String modele;
    private int annee;
   
    private String immatriculation;
    private double tarifHoraire;
    
	@Column(columnDefinition ="TEXT")
	 private Date dateMiseEnService;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public double getTarifHoraire() {
		return tarifHoraire;
	}

	public void setTarifHoraire(double tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	public Date getDateMiseEnService() {
		return dateMiseEnService;
	}

	public void setDateMiseEnService(Date dateMiseEnService) {
		this.dateMiseEnService = dateMiseEnService;
	}
	
	



}
