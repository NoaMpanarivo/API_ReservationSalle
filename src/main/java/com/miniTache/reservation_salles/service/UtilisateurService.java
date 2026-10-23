package com.miniTache.reservation_salles.service;


import com.miniTache.reservation_salles.Enum.Role;
import com.miniTache.reservation_salles.Repository.UtilisateurRepository;
import com.miniTache.reservation_salles.modele.Utilisateur;
import com.miniTache.reservation_salles.request.UtilisateurRequest;
import com.miniTache.reservation_salles.response.UtilisateurResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional
    public UtilisateurResponse enregistrerUsers(UtilisateurRequest request){

        if(repository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Cet email est deja utilise");
        }

        Utilisateur user = new Utilisateur();

        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.ROLE_USER);

        Utilisateur sauvegardee = repository.save(user);

        return UtilisateurResponse.from(sauvegardee);

    }
}
