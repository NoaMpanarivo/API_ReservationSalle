package com.miniTache.reservation_salles.securite;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ConnexionController {

    private final ConnexionService connexionService;

    public ConnexionController(ConnexionService connexionService) {
        this.connexionService = connexionService;
    }

    @PostMapping("/login")
    public ConnexionResponse connecter(
            @RequestBody ConnexionRequest request) {

        String token = connexionService.connecter(
                request.email(),
                request.password()
        );

        return new ConnexionResponse(token, "Bearer");
    }
}