package com.hosting.rest.api.models.Accomodation.SavedAccomodation;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.User.UserHostModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime createdAt;
}
