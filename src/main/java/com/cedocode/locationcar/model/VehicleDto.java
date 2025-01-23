package com.cedocode.locationcar.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class VehicleDto {
	
	private Long id;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "La marque est requise" )
	private String marque;
	
	
	@NotEmpty(message = "La modèle est requis" )
	private String modele;
	   
	
	@NotNull (message = "L'année de fabrication est requise" )
	private int annee;
	   
	    
	@NotEmpty(message = "L'immatriculation est requise" )
	private String immatriculation;
	   
	
	@Min(value = 0,message = "Le tarif horaire doit être positif" )
	private double tarifHoraire;
	    
	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Spécifie le format de la date ici
    private Date dateMiseEnService;

		

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
