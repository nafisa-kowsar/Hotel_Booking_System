package com.hexaware.ccozyhaven.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hexaware.ccozyhaven.entities.Administrator;
import com.hexaware.ccozyhaven.entities.HotelOwner;
import com.hexaware.ccozyhaven.entities.User;

import com.hexaware.ccozyhaven.repository.AdministratorRepository;
import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;

import com.hexaware.ccozyhaven.repository.UserRepository;

@Component
public class UsersInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private HotelOwnerRepository hotelOwnerRepo;

    @Autowired
    private AdministratorRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	 Optional<User> user = userRepo.findByUsername(username);
         if (user.isPresent()) {
             return new UserInfoDetails(user.get());
         }

         // Check if the username exists in the admin table
         Optional<Administrator> admin = adminRepo.findByUsername(username);
         if (admin.isPresent()) {
             return new AdminInfoDetails(admin.get());
         }
         
         Optional<HotelOwner> hotelOwner = hotelOwnerRepo.findByUsername(username);
         if (hotelOwner.isPresent()) {
             return new HotelOwnerInfoDetails(hotelOwner.get());
         }

         // If the username doesn't exist in either table, throw an exception
         throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
