package com.clarissek.airbnb_clone.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Room {
  private Long id;
  private String roomType;
  private BigDecimal roomPrice;
  private boolean isBooking = false;
  private List<BookingRoom> bookings;

  public Room() {
    this.bookings = new ArrayList<>();
  }


}
