package com.clarissek.airbnb_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clarissek.airbnb_clone.model.Room;


public interface RoomRepository extends JpaRepository<Room, Long> {

}
