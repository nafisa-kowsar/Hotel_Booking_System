package com.hexaware.ccozyhaven.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ccozyhaven.dto.PasswordDTO;
import com.hexaware.ccozyhaven.service.IPasswordService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/password")
public class PasswordController {
	
	@Autowired
    private IPasswordService passwordUpdateService;

    @PutMapping("/update")
    public String updatePassword(@RequestBody PasswordDTO passwordDTO) {
       return passwordUpdateService.updatePassword(passwordDTO);
        
    }

}
