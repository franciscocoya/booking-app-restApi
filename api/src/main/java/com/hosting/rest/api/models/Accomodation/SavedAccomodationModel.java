package com.hosting.rest.api.models.Accomodation;


import com.hosting.rest.api.models.User.UserHostModel;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SAVED_ACCOMODATION")
@Data
public class SavedAccomodationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    // TODO: RELACION
    @JoinColumn(name = "ID")
    private UserHostModel idUser;

    // TODO: RELACION
    @JoinColumn(name = "REG_NUM")
    private AccomodationModel idAccomodation;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
