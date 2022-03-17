package com.hosting.rest.api.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "APP_USER")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotBlank
    @Column(name = "UNAME")
    private String name;

    @NotBlank
    @Column(name = "SURNAME")
    private String surname;

    @NotBlank
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Column(name = "PHONE")
    private String phone;

    @NotBlank
    @Column(name = "PASS")
    private String pass;

    @Column(name = "PROFILE_IMG")
    private String profileImage;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private Timestamp createdAt;
}
