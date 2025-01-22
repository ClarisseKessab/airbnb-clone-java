package com.clarissek.airbnb_clone.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


import com.clarissek.airbnb_clone.model.Room;
import com.clarissek.airbnb_clone.response.RoomResponse;
import com.clarissek.airbnb_clone.service.IRoomService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/rooms")
@RestController
public class RoomController {

  private final IRoomService roomService;

  @PostMapping("/add/new-room")
  public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,@RequestParam("roomType") String roomType,@RequestParam("roomPrice") BigDecimal roomPrice) throws IOException, SQLException{
    Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
    RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/room-type")
  public ResponseEntity<List<String>> getRoomTypes() {
    List<String> roomTypes = roomService.getAllRoomTypes();
    return ResponseEntity.ok(roomTypes);
}

}
