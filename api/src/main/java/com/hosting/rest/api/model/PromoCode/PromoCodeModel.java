package com.hosting.rest.api.model.PromoCode;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "PROMO_CODE")
public class PromoCodeModel {

    @Id
    @Column(name = "SERIAL_NUM")
    private int serial_num;

    @Column(name = "AMOUNT_PERCENTAGE")
    private BigDecimal amountPercentange;

    @Column(name = "DATE_START")
    private Timestamp dateStart;

    @Column(name = "DATE_END")
    private Timestamp dateEnd;

    @Column(name = "ID_ACC")
    private int idAcc;

    @Column(name = "ID_USER")
    private int idUser;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
