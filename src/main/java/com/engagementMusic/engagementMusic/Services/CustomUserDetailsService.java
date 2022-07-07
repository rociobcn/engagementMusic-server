package com.engagementMusic.engagementMusic.Services;

import com.engagementMusic.engagementMusic.Models.User;
import com.engagementMusic.engagementMusic.Repositories.UserRepository;
import com.engagementMusic.engagementMusic.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (!user.isPresent()) {
            System.out.println("User not present!");
            throw new UsernameNotFoundException("User does not exist");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
        System.out.println("User found");
        System.out.println(customUserDetails.getAuthorities());
        System.out.println(customUserDetails.getUsername());

        return customUserDetails;
    }
}
