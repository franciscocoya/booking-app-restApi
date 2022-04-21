package com.hosting.rest.api.models.Search;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "APP_SEARCH")
public class SearchModel implements Serializable {

	private static final long serialVersionUID = -7117461569077481959L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer idSearch;
	
	@Column(name = "WORD")
	private String word;
	
	@Column(name = "CREATED_AT")
	@CreatedDate
	private LocalDateTime createdAt;
}
