package com.hosting.rest.api.models.Accomodation;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @Column(name = "ID_USER")
    private Integer idUser;

    @Column(name = "ID_ACC")
    private String idAccomodation;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
