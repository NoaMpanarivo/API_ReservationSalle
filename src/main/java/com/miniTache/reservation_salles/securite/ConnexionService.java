package com.miniTache.reservation_salles.securite;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ConnexionService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ConnexionService(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String connecter(String email, String password) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                email, password
                        )
                );

        return jwtService.genererToken(authentication);
    }
}