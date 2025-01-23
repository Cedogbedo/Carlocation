package com.cedocode.locationcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cedocode.locationcar.model.Rental;
import com.cedocode.locationcar.model.RentalDto;
import com.cedocode.locationcar.model.User;
import com.cedocode.locationcar.model.Vehicle;
import com.cedocode.locationcar.servicesss.CustomUserDetails;
import com.cedocode.locationcar.servicesss.RentalServicess;
import com.cedocode.locationcar.servicesss.UserServices;
import com.cedocode.locationcar.servicesss.VehicleServices;
import com.cedocode.locationcar.services.RentalRepository;

@Controller
public class RentalController {

    private final VehicleServices vehicleServices;
    private final UserServices userServices;
    private final RentalRepository rentalService;

    @Autowired
    private RentalServicess rentalServices;
    
    
    // Constructor-based injection
    public RentalController(VehicleServices vehicleServices, UserServices userServices, RentalRepository rentalService) {
        this.vehicleServices = vehicleServices;
        this.userServices = userServices;
        this.rentalService = rentalService;
    }

    @GetMapping("/client/rent")
    public String getRentPage(@RequestParam Long vehicleId, Model model) {
        // Retrieve vehicle by its ID
        Vehicle vehicle = vehicleServices.findById(vehicleId);
        model.addAttribute("vehicle", vehicle);

        // Get the currently authenticated user
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userServices.findByEmail(email);
        model.addAttribute("user", user); // Add user info to the model

        // Create a new RentalDto object for the form
        RentalDto rentalDto = new RentalDto(); // Ensure a proper DTO exists
        model.addAttribute("rentalDto", rentalDto);

        return "client/rentform"; // The name of your rental form HTML page
    }
    
    
    @PostMapping("/client/rent")
    public String rentVehicle(@ModelAttribute RentalDto rentalDto, Model model) {
        System.out.println("Vehicle ID: " + rentalDto.getVehicleId()); // Debug line

        // Get the currently authenticated user
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userServices.findByEmail(email);

        // Create a new rental
        Rental rental = new Rental();
        rental.setUser(user);

        // Here, vehicleServices.findById() could throw the null error if vehicleId is null
        Vehicle vehicle = vehicleServices.findById(rentalDto.getVehicleId());
        if (vehicle == null) { 
            // Handle the case where the vehicle is not found
            model.addAttribute("errorMessage", "Vehicle not found");
            return "client/rentform"; // Optionally return to the rent form with an error
        }
        rental.setVehicle(vehicle);
        rental.setDateDebut(rentalDto.getDateDebut());
        rental.setDateFin(rentalDto.getDateFin());
        rental.setChauffeur(rentalDto.isChauffeur());

        // Save the rental
        rentalService.save(rental);

        return "redirect:/client/clienthome"; // Redirect to the client's homepage
    }
    
    
    @GetMapping("/client/my-rentals")
    public String showClientRentals(Model model) {
    	 // Obtenir l'utilisateur connecté
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Cast auth.getPrincipal() to CustomUserDetails
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        
        // Obtenir l'objet User à partir de CustomUserDetails
        User currentUser = userDetails.getUser(); 

        // Récupérer les locations de cet utilisateur triées par ID en ordre décroissant
        List<Rental> clientRentals = rentalServices.findByUser(currentUser, Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("rentals", clientRentals);

        return "client/rentalclient";  // Vue pour afficher les locations du client
    }
    
    @GetMapping("/admin/rentals")
    public String showRentalsForAdmin(Model model) {
        List<Rental> rentals = rentalService.findAll(Sort.by(Sort.Direction.DESC, "id")); 
        model.addAttribute("rentals", rentals);
        return "admin/rentals";
    }
    

    @GetMapping("/admin/return/{id}")
    public String returnVehicle(@PathVariable Long id, Model model) {
        // Récupérer la location par ID
        Rental rental = rentalServices.findById(id); 
        
        // Calculer les frais totaux
        double totalFees = rentalServices.calculateTotalFees(rental);
        
        // Ajouter les informations dans le modèle
        model.addAttribute("rental", rental);
        model.addAttribute("totalFees", totalFees);
        
        // Rediriger vers la page de facture
        return "admin/facture";
    }
    
    @GetMapping("/client/return/{id}")
    public String clientreturnVehicle(@PathVariable Long id, Model model) {
        // Récupérer la location par ID
        Rental rental = rentalServices.findById(id); 
        
        // Calculer les frais totaux
        double totalFees = rentalServices.calculateTotalFees(rental);
        
        // Ajouter les informations dans le modèle
        model.addAttribute("rental", rental);
        model.addAttribute("totalFees", totalFees);
        
        // Rediriger vers la page de facture
        return "client/factureclient";
    }

    
}

