package com.cedocode.locationcar.servicesss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cedocode.locationcar.model.User;
import com.cedocode.locationcar.services.UserRepository;


@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User found: " + user.getEmail()); // Debug line
        return new CustomUserDetails(user);
    }

    

}
