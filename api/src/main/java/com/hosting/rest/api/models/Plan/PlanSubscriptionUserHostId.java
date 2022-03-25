package com.hosting.rest.api.models.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class PlanSubscriptionUserHostId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_PLAN")
    private Integer idPlan;

    @Column(name = "ID_USER")
    private Integer idUserHost;
}
