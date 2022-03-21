package com.hosting.rest.api.models.PromoCode;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.User.UserHostModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "ID_ACC")
    private AccomodationModel idAcc;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserHostModel idUser;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
