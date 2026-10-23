package com.miniTache.reservation_salles.Repository;

import com.miniTache.reservation_salles.modele.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
