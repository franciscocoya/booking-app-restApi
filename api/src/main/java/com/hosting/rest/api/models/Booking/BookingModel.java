package com.hosting.rest.api.models.Booking;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "BOOKING" )
@Data
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CHECK_IN")
    private Timestamp checkIn;

    @Column(name = "CHECK_OUT")
    private Timestamp checkOut;

    @Column(name = "GUESTS")
    private Integer numOfGuests;

    @Column(name = "BILL_NUM")
    private String billNumber;

    @Column(name = "ID_HOST")
    private Integer idHost;

    @Column(name = "ID_ACCOMODATION")
    private String idAccomodation;
}
