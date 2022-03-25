package com.hosting.rest.api.models.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.hosting.rest.api.models.Payment.PaymentModel;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "BOOKING_BILL" )
public class BookingBillModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BILL_NUM")
    private String billNumber;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "SERVICE_FEE")
    private BigDecimal serviceFee;

    @Column(name = "DISCCOUNT")
    private BigDecimal disccount;

    @Column(name = "PAYMENT_ID")
    private PaymentModel paymentId;
}
