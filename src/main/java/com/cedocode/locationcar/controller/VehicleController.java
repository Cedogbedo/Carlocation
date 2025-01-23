package com.cedocode.locationcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.cedocode.locationcar.model.Vehicle;
import com.cedocode.locationcar.model.VehicleDto;
import com.cedocode.locationcar.services.VehicleRepository;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/admin")

public class VehicleController {
	
	
	@Autowired
	private VehicleRepository repo;


	@GetMapping({"","/"})
	public String showVehicleList (Model model) {
	List<Vehicle> vehicle = repo.findAll(Sort.by(Sort.Direction.DESC, "id")) ;
	model.addAttribute ("vehicle", vehicle) ; 
	
	
	
	return "admin/home" ;
	}
	
	// Créer un nouveau véhicule
    @GetMapping("/addV")
    public String showCreateForm(Model model) {
    	 VehicleDto vehicleDto = new VehicleDto();	
    	    model.addAttribute("vehicleDto",vehicleDto);
        return "admin/addVehicle"; // page pour le formulaire de création
    }

 
    @PostMapping("/addV")
    public String addVehicle(@Valid @ModelAttribute VehicleDto vehicleDto)
        {
    	Vehicle vehicle = new Vehicle();
        vehicle.setModele(vehicleDto.getModele());
        vehicle.setMarque(vehicleDto.getMarque());
        vehicle.setAnnee(vehicleDto.getAnnee());
        vehicle.setTarifHoraire(vehicleDto.getTarifHoraire());
        vehicle.setImmatriculation(vehicleDto.getImmatriculation());
        vehicle.setDateMiseEnService(vehicleDto.getDateMiseEnService());
        repo.save(vehicle); // Sauvegarde l'entité transformée
        
        return "redirect:/admin/home";
    }
    
       @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Vehicle vehicle = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle ID:" + id));
        
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId()); // Assurez-vous que l'id est bien défini
        vehicleDto.setModele(vehicle.getModele());
        vehicleDto.setMarque(vehicle.getMarque());
        vehicleDto.setAnnee(vehicle.getAnnee());
        vehicleDto.setTarifHoraire(vehicle.getTarifHoraire());
        vehicleDto.setImmatriculation(vehicle.getImmatriculation());
        vehicleDto.setDateMiseEnService(vehicle.getDateMiseEnService());

        model.addAttribute("vehicleDto", vehicleDto);
        return "admin/editVehicle"; // Vue de formulaire de modification
    }
    @PostMapping("/edit/{id}")
    public String updateVehicle(@PathVariable("id") Long id, @Valid @ModelAttribute("vehicleDto") VehicleDto vehicleDto, BindingResult result) {
        if (result.hasErrors()) {
            // Si des erreurs sont présentes, renvoyez le formulaire avec les messages d'erreur
            return "admin/editVehicle"; // Renvoie à la vue de modification si des erreurs sont détectées
        }

        Vehicle vehicle = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle ID:" + id));

        // Mettez à jour les propriétés du véhicule
        vehicle.setModele(vehicleDto.getModele());
        vehicle.setMarque(vehicleDto.getMarque());
        vehicle.setAnnee(vehicleDto.getAnnee());
        vehicle.setTarifHoraire(vehicleDto.getTarifHoraire());
        vehicle.setImmatriculation(vehicleDto.getImmatriculation());
        vehicle.setDateMiseEnService(vehicleDto.getDateMiseEnService());
        
        // Enregistrez les modifications
        repo.save(vehicle);
        return "redirect:/admin"; // Redirection vers la page admin après la mise à jour
    }

 // Supprimer un véhicule par ID
    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable("id") Long id) {
        repo.deleteById(id); // Supprime le véhicule correspondant
        return "redirect:/admin"; // Redirige vers la page admin après suppression
    }

	
	
	
}
