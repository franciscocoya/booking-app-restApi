package com.hosting.rest.api.models.User;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "USER_REVIEW")
public class HostReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "STARS")
    private int stars;

    @ManyToOne
    @JoinColumn(name = "ID_USER_A")
    private UserHostModel idUserA;

    @ManyToOne
    @JoinColumn(name = "ID_USER_B")
    private UserModel idUserB;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
