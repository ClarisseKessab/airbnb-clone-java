package com.clarissek.airbnb_clone.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingRoomResponse {

  private Long id;
  private LocalDate startDate;
  private LocalDate endDate;
  private String guestFullName;
  private String guestEmail;
  private String guestPhone;
  private int numOfAdults;
  private int numOfChildren;
  private int totalNumOfGuest;
  private String bookingConfirmationCode;
  private RoomResponse room;

  public BookingRoomResponse(Long id, LocalDate startDate, LocalDate endDate, String bookingConfirmationCode) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.bookingConfirmationCode = bookingConfirmationCode;
  }


}
