package com.cedocode.locationcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cedocode.locationcar.model.UserDto;
import com.cedocode.locationcar.servicesss.UserServices;



@Controller
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "connect/register";  // Your registration form page
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserDto userDto, Model model) {
        // Validate password match
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            model.addAttribute("message", "Passwords do not match");
            return "connect/register"; // Return to registration page
        }

        
        userDto.setRole("ROLE_USER");
        // Save user
        userServices.save(userDto);
        model.addAttribute("message", "Registration successful!");
        
        // Redirect to login page
        return "redirect:/login"; // Redirect to avoid form resubmission
    }
    
    @GetMapping("/login")
    public String getLoginPage() {
        return "connect/login"; // Page du formulaire de connexion
    }
    
    @GetMapping("/admin/home")
    public String getAdminHomePage(Model model) {
        // Redirect to the vehicle list method in the VehicleController
        return "redirect:/admin"; // This should match the VehicleController mapping for vehicle list
    }

    
    
}
