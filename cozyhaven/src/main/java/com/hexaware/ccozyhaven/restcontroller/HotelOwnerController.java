package com.hexaware.ccozyhaven.restcontroller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ccozyhaven.dto.HotelOwnerDTO;
import com.hexaware.ccozyhaven.entities.HotelOwner;

import com.hexaware.ccozyhaven.dto.AuthRequest;

import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerNotFoundException;

import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;
import com.hexaware.ccozyhaven.service.IHotelOwnerService;

import com.hexaware.ccozyhaven.service.JwtService;
/*
 * Author: Nafisa
 * 
 * Controller description: Handles HTTP requests related to the HotelOwner entity.
 * It contains methods for registering a new HotelOwner, logging in, updating details, etc.
 */

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/hotelowner")
public class HotelOwnerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HotelOwnerController.class);

	private final IHotelOwnerService hotelOwnerService;

	@Autowired
	public HotelOwnerController(IHotelOwnerService hotelOwnerService) {
		this.hotelOwnerService = hotelOwnerService;
	}

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	HotelOwnerRepository hotelOwnerRepository;

	@PostMapping("/register")
	public String registerCustomer(@RequestBody HotelOwnerDTO hotelOwnerDTO) throws DataAlreadyPresentException {
		LOGGER.info("Request Received to register new Hotel Owner: {}",  hotelOwnerDTO);
		long hotelOwnerId = hotelOwnerService.registerHotelOwner(hotelOwnerDTO);

		 if (hotelOwnerId != 0) {
		        return "{\"message\":\"Hotel Owner registered successfully\"}";
		    } else {
		    	return "{\"message\":\"Hotel Owner registration failed\"}";
		        
		    }

	}

	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = 	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		 
		String token = null;
		
				if(authentication.isAuthenticated()) {
					
				 
					 Optional<HotelOwner> hotelOwner = hotelOwnerRepository.findByUsername(authRequest.getUsername());

				        if (hotelOwner.isPresent()) {
				            String role = hotelOwner.get().getRole();
				            Long customerId = hotelOwner.get().getHotelOwnerId();

				           
				            token = jwtService.generateToken(authRequest.getUsername(), role, customerId);

				            LOGGER.info("Token: " + token);
				        } else {
				            LOGGER.error("User not found in the database");
				            
				        }
					
				}
				else {
					
					LOGGER.info("invalid");
					
					 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");
					
				}
		
				return token;
	}

	@PutMapping("/update/{hotelOwnerId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER')")
	public ResponseEntity<String> updateHotelOwner(@PathVariable Long hotelOwnerId,
			@RequestBody HotelOwnerDTO updatedHotelOwnerDTO) {
		try {
			hotelOwnerService.updateHotelOwnerWithHotel(hotelOwnerId, updatedHotelOwnerDTO);
			return new ResponseEntity<>("HotelOwner updated successfully", HttpStatus.OK);
		} catch (HotelOwnerNotFoundException e) {
			LOGGER.error("Error updating HotelOwner with ID: {}", hotelOwnerId, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{hotelOwnerId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER')")
	public ResponseEntity<String> deleteHotelOwner(@PathVariable Long hotelOwnerId) {
		try {
			LOGGER.info("delete hotel owner");
			hotelOwnerService.deleteHotelOwner(hotelOwnerId);
			return new ResponseEntity<>("Hotel owner deleted successfully", HttpStatus.OK);
		} catch (HotelOwnerNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	 @GetMapping("/get-by-id/{hotelOwnerId}")
	    public ResponseEntity<HotelOwnerDTO> getHotelOwnerById(@PathVariable Long hotelOwnerId) {
	        try {
	            HotelOwnerDTO hotelOwnerDTO = hotelOwnerService.findById(hotelOwnerId);
	            return ResponseEntity.ok(hotelOwnerDTO);
	        } catch (HotelOwnerNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
