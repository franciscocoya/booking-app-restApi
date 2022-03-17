package com.hosting.rest.api.models.Booking;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table( name = "BOOKING_BILL" )
@Data
public class BookingBillModel {

    @Id
    @Column(name = "BILL_NUM")
    private String billNumber;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "SERVICE_FEE")
    private BigDecimal serviceFee;

    @Column(name = "DISCCOUNT")
    private BigDecimal disccount;

    @Column(name = "PAYMENT_ID")
    private Integer paymentId;
}
