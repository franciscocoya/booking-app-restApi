package com.hosting.rest.api.models.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table( name = "PAYMENT" )
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idPayment;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
