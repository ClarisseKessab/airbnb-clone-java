package com.clarissek.airbnb_clone.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookingRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="start_Date")
  private LocalDate startDate;
  @Column(name="end_Date")
  private LocalDate endDate;
  @Column(name="guest_FullName")
  private String guestFullName;
  @Column(name="guest_Email")
  private String guestEmail;
  @Column(name="guest_Phone")
  private String guestPhone;
  @Column(name="adults")
  private int numOfAdults;
  @Column(name="children")
  private int numOfChildren;
  @Column(name="total_Guest")
  private int totalNumOfGuest;
  @Column(name="confirmation_Code")
  private String bookingConfirmationCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="room_id")
  private Room room;

  public void calculateTotalNumOfGuest(){
    this.totalNumOfGuest = this.numOfAdults + this.numOfChildren;
  }

  public void setNumOfAdults(int numOfAdults) {
    this.numOfAdults = numOfAdults;
    calculateTotalNumOfGuest();
  }

  public void setNumOfChildren(int numOfChildren) {
    this.numOfChildren = numOfChildren;
    calculateTotalNumOfGuest();
  }

  public void setBookingConfirmationCode(String bookingConfirmationCode) {
    this.bookingConfirmationCode = bookingConfirmationCode;
  }
  




}
