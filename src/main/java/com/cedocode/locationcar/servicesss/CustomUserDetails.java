package com.cedocode.locationcar.servicesss;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import com.cedocode.locationcar.model.User;
import org.springframework.security.core.userdetails.UserDetails;



public class CustomUserDetails implements UserDetails, Serializable {
    
	private static final long serialVersionUID = 1L; // Add this line
    private final User user;

public CustomUserDetails(User user) {
        this.user = user;
    }

public User getUser() {
    return this.user;
}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
