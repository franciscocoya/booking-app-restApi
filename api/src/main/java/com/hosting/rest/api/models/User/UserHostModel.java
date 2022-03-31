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

	@Column(name = "DNI")
	private String dni;

	@Column(name = "BIO")
	private String bio;

	@Column(name = "IS_DNI_VERIFIED")
	private boolean isDniVerified;

	@Column(name = "IS_EMAIL_VERIFIED")
	private boolean isEmailVerified;

	@Column(name = "IS_PHONE_VERIFIED")
	private boolean isPhoneVerified;

	@Column(name = "IS_VERIFIED")
	private boolean isVerified;

	@Column(name = "DIRECTION")
	private String direction;
}
