package com.clarissek.airbnb_clone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarissek.airbnb_clone.model.BookingRoom;
import com.clarissek.airbnb_clone.repository.BookingRoomRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    public List<BookingRoom> getAllBookingsByRoomId(Long roomId) {
        return bookingRoomRepository.findByRoomId(roomId);
    }
}

