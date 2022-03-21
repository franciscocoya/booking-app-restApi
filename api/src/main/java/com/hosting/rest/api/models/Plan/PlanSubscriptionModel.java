package com.hosting.rest.api.models.Plan;

import com.hosting.rest.api.models.User.UserHostModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "PLAN_SUBSCRIPTION")
public class PlanSubscriptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private UserHostModel idUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private PlanModel idPlan;
}
