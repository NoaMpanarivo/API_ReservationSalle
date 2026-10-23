package com.miniTache.reservation_salles.response;

import com.miniTache.reservation_salles.modele.Room;

public record RoomResponse(
        Long idRoom,
        String name,
        Integer capacity,
        Integer floor
){
    public static RoomResponse from(Room room){
        return new RoomResponse(
                room.getIdRoom(),
                room.getName(),
                room.getCapacity(),
                room.getFloor()
        );
    }
}
