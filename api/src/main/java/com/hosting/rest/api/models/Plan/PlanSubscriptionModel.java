package com.hosting.rest.api.models.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "PLAN_SUBSCRIPTION")
public class PlanSubscriptionModel {

    @EmbeddedId
    private PlanSubscriptionUserHostId planSubscriptionUserHostId;
}
