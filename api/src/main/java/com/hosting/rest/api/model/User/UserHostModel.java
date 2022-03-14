package com.hosting.rest.api.model.User;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@DiscriminatorValue("1")
@Table(name = "USER_HOST")
public class UserHostModel extends UserModel{

    @Column(name = "BIO")
    private String bio;

    @Column(name = "VERIFIED")
    private boolean isVerified;

    @Column(name = "DIRECTION")
    private String direction;
}
