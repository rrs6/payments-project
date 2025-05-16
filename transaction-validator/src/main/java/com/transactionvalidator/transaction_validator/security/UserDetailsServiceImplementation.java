package com.transactionvalidator.transaction_validator.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.transactionvalidator.transaction_validator.db.entities.User;
import com.transactionvalidator.transaction_validator.db.repositories.UserRepository;

@Service
public class UserDetailsServiceImplementation  implements UserDetailsService{

    private final UserRepository userRepository;
    
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetailsImplementation loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDetailsImplementation(user);
    }

}
