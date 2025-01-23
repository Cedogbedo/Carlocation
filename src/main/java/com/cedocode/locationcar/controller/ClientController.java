package com.cedocode.locationcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cedocode.locationcar.model.Vehicle;
import com.cedocode.locationcar.services.VehicleRepository;


@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private VehicleRepository repo;
	
	  @GetMapping("/clienthome")
	    public String showClientHome(Model model) {
		  List<Vehicle> vehicle = repo.findAll(Sort.by(Sort.Direction.DESC, "id")) ;
			model.addAttribute ("vehicle", vehicle) ; 
	        return "client/clienthome"; // Name of the  template for client home
	    }
	

}
