package com.hosting.rest.api.models.Plan;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APP_PLAN")
public class PlanModel implements Serializable{

	private static final long serialVersionUID = -944756623330853898L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer idPlan;

	@Enumerated(EnumType.STRING)
	@Column(name = "PLAN_TYPE")
	private PlanType planType;

	@Column(name = "PRICE")
	private BigDecimal price;
}
