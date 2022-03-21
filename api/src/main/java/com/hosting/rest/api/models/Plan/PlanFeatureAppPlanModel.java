package com.hosting.rest.api.models.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "PLAN_FEATURE")
public class PlanFeatureAppPlanModel {

    // TODO: Establecer la clave primaria de la entidad y las relaciones entre ambas tablas.

    @JoinColumn(name = "ID_PLAN")
    private PlanModel idPlan;

    @JoinColumn(name = "ID_PLAN_FEATURE")
    private PlanFeatureModel idPlanFeature;
}
