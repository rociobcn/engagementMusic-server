package com.engagementMusic.engagementMusic.Controllers;

import com.engagementMusic.engagementMusic.Models.Role;
import com.engagementMusic.engagementMusic.Models.User;
import com.engagementMusic.engagementMusic.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/get-user-info-role")
    @CrossOrigin
    public String showToUsersRole(@AuthenticationPrincipal UserDetails userDetails) {
        return userRepository.findByUsername(userDetails.getUsername()).get().getRoles().iterator().next().getRole();
    }
    @GetMapping("/login")
    @CrossOrigin
    public User showToUsers(@AuthenticationPrincipal UserDetails userDetails) {
        return userRepository.findByUsername(userDetails.getUsername()).get();
    }
}
