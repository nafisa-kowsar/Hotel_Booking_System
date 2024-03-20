package com.hexaware.ccozyhaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ccozyhaven.dto.PasswordDTO;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PasswordServiceImp  implements IPasswordService{
	
	 @Autowired
	    private IUserService userService;

	    
	    

	    @Override
	    public String updatePassword(PasswordDTO passwordDTO) {
	        String username = passwordDTO.getUsername();
	        String newPassword = passwordDTO.getNewPassword();

	        if (userService.checkIfUserExists(username)) {
	            return userService.updateUserPassword(username, newPassword);
	       
	        } else {
	            return "User not found";
	        }
	    }
	
	

}
