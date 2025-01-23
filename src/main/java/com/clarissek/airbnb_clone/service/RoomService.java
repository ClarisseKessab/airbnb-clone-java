package com.clarissek.airbnb_clone.service;

import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;
import com.clarissek.airbnb_clone.model.Room;
import com.clarissek.airbnb_clone.exception.ResourceNotFoundException;
import com.clarissek.airbnb_clone.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import java.sql.Blob;
import java.io.IOException;
import java.sql.SQLException;


import javax.sql.rowset.serial.SerialBlob;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;

  @Override
  public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException {
      Room room = new Room();
      room.setRoomType(roomType);
      room.setRoomPrice(roomPrice);
      if (!file.isEmpty()) {
          byte[] photoBytes = file.getBytes();
          Blob photoBlob = new SerialBlob(photoBytes);
          room.setPhoto(photoBlob);
      }
      return roomRepository.save(room);
  }

  @Override
    public List<String> getAllRoomTypes() {
    return roomRepository.findDistinctRoomTypes();
  }

  @Override
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  @Override
  public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
    Optional<Room> theRoom = roomRepository.findById(roomId);
    if(theRoom.isEmpty()){
      throw new ResourceNotFoundException("Sorry, Room not found");
    }
    Blob photoBlob = theRoom.get().getPhoto();
    if(photoBlob != null){
      return photoBlob.getBytes(1,(int)photoBlob.length());
    }
    return null;
  }

}
