package com.miniTache.reservation_salles.controller;

import com.miniTache.reservation_salles.request.RoomRequest;
import com.miniTache.reservation_salles.response.RoomResponse;
import com.miniTache.reservation_salles.service.RoomService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomResponse> listerSalles() {
        return roomService.listerSalles();
    }

    @GetMapping("/{id}")
    public RoomResponse trouverSalle(@PathVariable Long id) {
        return roomService.trouverSalle(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public RoomResponse creerSalle(@Valid @RequestBody RoomRequest request) {
        return roomService.creerSalle(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomResponse modifierSalle(
            @PathVariable Long id,
            @Valid @RequestBody RoomRequest request
    ) {
        return roomService.modifierSalle(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimerSalle(@PathVariable Long id) {
        roomService.supprimerSalle(id);
    }
}