package com.hosting.rest.api.models.User;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_REVIEW")
public class HostReviewModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "STARS")
	private int stars;

	@ManyToOne
	@JoinColumn(name = "ID_USER_A")
	private UserHostModel idUserA;

	@ManyToOne
	@JoinColumn(name = "ID_USER_B")
	private UserModel idUserB; // Usuario que recibe la valoracion

	@Column(name = "CREATED_AT")
	@CreatedDate
	private LocalDateTime createdAt;
}
