package com.hosting.rest.api.models.Plan;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PlanSubscriptionUserHostId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_PLAN")
    private Integer idPlan;

    @Column(name = "ID_USER")
    private Integer idUserHost;
}
