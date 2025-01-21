package com.clarissek.airbnb_clone.response;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import com.clarissek.airbnb_clone.model.BookingRoom;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Base64;


@Data
@NoArgsConstructor
public class RoomResponse {
  private Long id;
  private String roomType;
  private BigDecimal roomPrice;
  private boolean isBooking = false;
  private String photo;
  private List<BookingRoomResponse> bookings;

  public RoomResponse(Long id, String roomType, BigDecimal roomPrice) {
    this.id = id;
    this.roomType = roomType;
    this.roomPrice = roomPrice;
  }

  public RoomResponse(Long id, String roomType, BigDecimal roomPrice, boolean isBooking, byte[] photoBytes,
      List<BookingRoomResponse> bookings) {
    this.id = id;
    this.roomType = roomType;
    this.roomPrice = roomPrice;
    this.isBooking = isBooking;
    this.photo = (photoBytes != null) ? Base64.getEncoder().encodeToString(photoBytes) : null;
    this.bookings = bookings;
  }




}
