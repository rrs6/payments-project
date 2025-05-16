package com.transactionvalidator.transaction_validator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.transactionvalidator.transaction_validator.config.SecurityConfig;
import com.transactionvalidator.transaction_validator.db.entities.Role;
import com.transactionvalidator.transaction_validator.db.entities.User;
import com.transactionvalidator.transaction_validator.db.repositories.RoleRepository;
import com.transactionvalidator.transaction_validator.db.repositories.UserRepository;
import com.transactionvalidator.transaction_validator.dtos.SignInRecord;
import com.transactionvalidator.transaction_validator.dtos.SignUpRecord;
import com.transactionvalidator.transaction_validator.security.JwtTokenService;
import com.transactionvalidator.transaction_validator.security.UserDetailsImplementation;
import com.transactionvalidator.transaction_validator.utils.enums.RoleEnum;

@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SecurityConfig securityConfig;

    public UserService(AuthenticationManager authenticationManager, 
    JwtTokenService jwtTokenService, UserRepository userRepository,
    SecurityConfig securityConfig, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
        this.roleRepository = roleRepository;
    }

    public String authenticateUser(SignInRecord signIn) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(signIn.email(), signIn.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        return jwtTokenService.generateToken(userDetails);   
    }


    public void createUser(SignUpRecord signUp) {
        User newUser = new User();
        List<Role> allRoles = getRules(signUp.roles());
        newUser.setEmail(signUp.email());
        newUser.setPassword(securityConfig.passwordEncoder().encode(signUp.password()));
        newUser.setRoles(allRoles);
        this.userRepository.save(newUser);
    }

    public List<Role> getRules(List<String> rules) {
        List<Role> allRoles = new ArrayList<>();
        rules.forEach((role) -> {
            Optional<Role> r = roleRepository.findByName(RoleEnum.valueOf(role));
            if(r.isEmpty()) {;
                Role newRole = new Role(role);
                allRoles.add(newRole);
                roleRepository.save(newRole);
            }else{
                allRoles.add(r.get());
            }
        });
        return allRoles;
    }
}
