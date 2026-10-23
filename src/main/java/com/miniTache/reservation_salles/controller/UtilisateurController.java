package com.miniTache.reservation_salles.controller;

import com.miniTache.reservation_salles.request.UtilisateurRequest;
import com.miniTache.reservation_salles.response.UtilisateurResponse;
import com.miniTache.reservation_salles.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service){
        this.service = service;
    }

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtilisateurResponse enregistrerUsers(@Valid @RequestBody UtilisateurRequest request){
        return service.enregistrerUsers(request);
    }
}
