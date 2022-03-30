package com.hosting.rest.api.models.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name = "USER_HOST")
@PrimaryKeyJoinColumn(name = "ID")
public class UserHostModel extends UserModel {

	@Column(name = "BIO")
	private String bio;

	@Column(name = "VERIFIED")
	private boolean isVerified;

	@Column(name = "DIRECTION")
	private String direction;
}
