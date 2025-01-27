package com.clarissek.airbnb_clone.service;

import java.math.BigDecimal;
import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.web.multipart.MultipartFile;
import com.clarissek.airbnb_clone.model.Room;
import java.io.IOException;
import java.sql.SQLException;


public interface IRoomService {
  Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException;
  List<String> getAllRoomTypes();
  List<Room> getAllRooms();
  byte[] getRoomPhotoByRoomId(Long roomId) throws RelationNotFoundException, SQLException;

}
