package com.clarissek.airbnb_clone.model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor


public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
   @Column(name="room_Type")
  private String roomType;
  @Column(name="room_Price")
  private BigDecimal roomPrice;
  @Column(name="isBooking")
  private boolean isBooking = false;

  @Lob
  private Blob photo;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<BookingRoom> bookings;

  public Room() {
    this.bookings = new ArrayList<>();
  }

  public void addBooking(BookingRoom booking){
    if (bookings == null){
      bookings = new ArrayList<>();
    }
    bookings.add(booking);
    booking.setRoom(this);
    isBooking = true;
    String bookingCode = RandomStringUtils.randomNumeric(10);
    booking.setBookingConfirmationCode(bookingCode);
  }

}
