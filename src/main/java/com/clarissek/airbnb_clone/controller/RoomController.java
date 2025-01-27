package com.clarissek.airbnb_clone.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.management.relation.RelationNotFoundException;

import com.clarissek.airbnb_clone.exception.PhotoRetrievalException;
import com.clarissek.airbnb_clone.model.BookingRoom;
import com.clarissek.airbnb_clone.model.Room;
import com.clarissek.airbnb_clone.response.BookingRoomResponse;
import com.clarissek.airbnb_clone.response.RoomResponse;
import com.clarissek.airbnb_clone.service.IRoomService;
import com.clarissek.airbnb_clone.service.BookingService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("/rooms")
@RestController
public class RoomController {

  private final IRoomService roomService;
  private final BookingService bookingService;

  @PostMapping("/add/new-room")
  public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,@RequestParam("roomType") String roomType,@RequestParam("roomPrice") BigDecimal roomPrice) throws IOException, SQLException{
    Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
    RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/room/types")
  public List<String> getRoomTypes() {
    return roomService.getAllRoomTypes();
  }

  public ResponseEntity<List<RoomResponse>> getAllRooms(){
    List<Room> rooms = roomService.getAllRooms();
    List<RoomResponse> roomResponses = new ArrayList<>();
    for(Room room : rooms){
      byte[] photoBytes = null;
        try {
            photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
        } catch (RelationNotFoundException | SQLException e) {
            continue;
        }
      if(photoBytes != null && photoBytes.length > 0){
        String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
        RoomResponse roomResponse = getRoomResponse(room);
        roomResponse.setPhoto(base64Photo);
        roomResponses.add(roomResponse);
      }
    }
    return ResponseEntity.ok(roomResponses);
  }

  private RoomResponse getRoomResponse(Room room){
    List<BookingRoom> bookings = getAllBookingsByRoomId(room.getId());
    List<BookingRoomResponse> bookingInfo = bookings.stream().map(booking -> new BookingRoomResponse(booking.getId(), booking.getStartDate(), booking.getEndDate(), booking.getBookingConfirmationCode())).toList();
    byte[] photoBytes = null;
    Blob photoBlob = room.getPhoto();
    if(photoBlob != null){
      try{
        photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
      }catch(SQLException e){
        throw new PhotoRetrievalException("Error retrieving photo");

      }
    }
    return new RoomResponse(room.getId(), room.getRoomType(), room.getRoomPrice(), room.isBooking(),photoBytes, bookingInfo);
    }

  private List<BookingRoom> getAllBookingsByRoomId(Long roomId){
    return bookingService.getAllBookingsByRoomId(roomId);
  }



}
