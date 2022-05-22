package com.hosting.rest.api.models.User;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_HOST")
@PrimaryKeyJoinColumn(name = "ID")
@DiscriminatorValue("H")
public class UserHostModel extends UserModel {

	private static final long serialVersionUID = 4779973744142659808L;

	@Id
	@Column(name = "ID")
	private Integer id;

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

	public UserHostModel(Integer id, String name, String surname, String email, String phone, UserConfigurationModel userConfig, String password,
			String dni, String direction) {
		super(id, name, surname, email, phone, userConfig, password);
		this.dni = dni;
		this.direction = direction;
	}

}
