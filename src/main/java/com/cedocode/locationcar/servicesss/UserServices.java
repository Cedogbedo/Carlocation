package com.cedocode.locationcar.servicesss;

import com.cedocode.locationcar.model.User;
import com.cedocode.locationcar.model.UserDto;

public interface UserServices {
	
	User save (UserDto userDto);
	User findByEmail(String email);

}
