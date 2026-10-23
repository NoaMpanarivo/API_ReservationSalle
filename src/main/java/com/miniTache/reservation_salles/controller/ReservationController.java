package com.miniTache.reservation_salles.controller;

import com.miniTache.reservation_salles.request.ReservationRequest;
import com.miniTache.reservation_salles.response.ReservationResponse;
import com.miniTache.reservation_salles.service.ReservationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> listerReservations(
            Authentication authentication
    ) {
        return reservationService.listerReservations(authentication);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse creerReservation(
            @RequestParam Long roomId,
            @Valid @RequestBody ReservationRequest request,
            Authentication authentication
    ) {
        return reservationService.creerReservation(
                roomId, request, authentication
        );
    }

    @DeleteMapping("/{id}")
    public ReservationResponse annulerReservation(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return reservationService.annulerReservation(id, authentication);
    }
}