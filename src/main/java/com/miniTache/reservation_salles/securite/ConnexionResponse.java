package com.miniTache.reservation_salles.securite;

public record ConnexionResponse(
        String accessToken,
        String tokenType
) {}