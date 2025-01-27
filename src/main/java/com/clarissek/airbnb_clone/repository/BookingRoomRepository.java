package com.clarissek.airbnb_clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clarissek.airbnb_clone.model.BookingRoom;

  @Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom, Long> {
    List<BookingRoom> findByRoomId(Long roomId);
}
