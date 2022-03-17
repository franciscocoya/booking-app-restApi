package com.hosting.rest.api.models.Payment;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "PAYMENT" )
@Data
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
