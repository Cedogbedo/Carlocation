package com.cedocode.locationcar.servicesss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cedocode.locationcar.model.User;
import com.cedocode.locationcar.model.UserDto;
import com.cedocode.locationcar.services.UserRepository;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(UserDto userDto) {
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = new User(userDto.getUsername(), userDto.getEmail(),userDto.getPhonenumber(), encodedPassword, userDto.getRole());
        return userRepository.save(user);
    }   
    
    public void createAdminUserIfNotExists() {
        if (userRepository.findByEmail("admin@example.com") == null) {
            // Ensure consistent usage of parameters
            User adminUser = new User("Admin", "admin@example.com","01234567", passwordEncoder.encode("adminPassword"), "ROLE_ADMIN");
            userRepository.save(adminUser);
        }
    }
    
   
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // Cette méthode doit exister dans UserRepository
    }
   


}
