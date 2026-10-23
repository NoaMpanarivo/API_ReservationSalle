package com.miniTache.reservation_salles.securite;

import com.miniTache.reservation_salles.Repository.UtilisateurRepository;
import com.miniTache.reservation_salles.modele.Utilisateur;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepository repository;

    public UtilisateurDetailsService(UtilisateurRepository repository ){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Utilisateur utilisateur = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        return User.withUsername(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .authorities(utilisateur.getRole().name())
                .build();
    }
}