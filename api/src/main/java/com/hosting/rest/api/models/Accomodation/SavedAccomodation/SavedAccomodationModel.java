package com.hosting.rest.api.models.Accomodation.SavedAccomodation;


import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.User.UserHostModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "SAVED_ACCOMODATION")
public class SavedAccomodationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserHostModel idUser;

    @ManyToOne
    @JoinColumn(name = "ID_ACC")
    private AccomodationModel idAccomodation;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
