package com.hosting.rest.api.models.Booking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.User.UserModel;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "BOOKING" )
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
    private BookingBillModel billNumber;

    @Column(name = "ID_HOST")
    private UserModel idHost;

    @Column(name = "ID_ACCOMODATION")
    private AccomodationModel idAccomodation;

    // TODO: Created_at - Actualizarlo en la base de datos y creat atributo.
}
