package com.transactionvalidator.transaction_validator.security;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.transactionvalidator.transaction_validator.config.SecurityConfig;
import com.transactionvalidator.transaction_validator.db.entities.User;
import com.transactionvalidator.transaction_validator.db.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter{
    private JwtTokenService jwtTokenService;
    private UserDetailsServiceImplementation userDetailService;

    public UserAuthenticationFilter(JwtTokenService jwtTokenService, UserDetailsServiceImplementation userDetailService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (!(token == null)) {
                String subject = jwtTokenService.getSubjectFromToken(token); // Obtém o assunto (neste caso, o nome de usuário) do token
                UserDetailsImplementation userDetails = this.userDetailService.loadUserByUsername(subject); // Cria um UserDetails com o usuário encontrado
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(403);
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfig.endpoints_auth_not_required).contains(requestURI);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
