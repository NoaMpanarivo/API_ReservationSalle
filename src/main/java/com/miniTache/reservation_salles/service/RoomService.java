package com.miniTache.reservation_salles.service;

import com.miniTache.reservation_salles.Repository.ReservationRepository;
import com.miniTache.reservation_salles.Repository.RoomRepository;
import com.miniTache.reservation_salles.modele.Room;
import com.miniTache.reservation_salles.request.RoomRequest;
import com.miniTache.reservation_salles.response.RoomResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public RoomService(
            RoomRepository roomRepository,
            ReservationRepository reservationRepository
    ) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomResponse> listerSalles() {
        return roomRepository.findAll()
                .stream()
                .map(RoomResponse::from)
                .toList();
    }

    public RoomResponse trouverSalle(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Salle introuvable"
                ));

        return RoomResponse.from(room);
    }

    public RoomResponse creerSalle(RoomRequest request) {
        verifierDonnees(request);

        String name = request.name().trim();

        if (roomRepository.existsByName(name)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ce nom de salle existe déjà"
            );
        }

        Room room = new Room();
        room.setName(name);
        room.setCapacity(request.capacity());
        room.setFloor(request.floor());

        return RoomResponse.from(roomRepository.save(room));
    }

    public RoomResponse modifierSalle(Long id, RoomRequest request) {
        verifierDonnees(request);

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Salle introuvable"
                ));

        String name = request.name().trim();

        if (roomRepository.existsByNameAndIdRoomNot(name, id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ce nom de salle existe déjà"
            );
        }

        room.setName(name);
        room.setCapacity(request.capacity());
        room.setFloor(request.floor());

        return RoomResponse.from(roomRepository.save(room));
    }

    public void supprimerSalle(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Salle introuvable"
                ));

        if (reservationRepository.existsByRoom_IdRoom(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Impossible de supprimer une salle possédant des réservations"
            );
        }

        roomRepository.delete(room);
    }

    private void verifierDonnees(RoomRequest request) {
        if (request == null
                || request.name() == null
                || request.name().isBlank()
                || request.capacity() == null
                || request.capacity() <= 0
                || request.floor() == null
                || request.floor() < 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nom, capacité positive et étage valide obligatoires"
            );
        }
    }
}