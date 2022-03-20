package com.hosting.rest.api.models.Accomodation;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.hosting.rest.api.models.User.UserHostModel;

import lombok.Data;

@Entity
@Table(name = "ACCOMODATION_REVIEW")
@Data
public class AccomodationReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "STARS")
    private Integer stars;

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
