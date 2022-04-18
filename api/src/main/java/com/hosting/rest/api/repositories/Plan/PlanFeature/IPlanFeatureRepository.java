package com.hosting.rest.api.repositories.Plan.PlanFeature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Plan.PlanFeatureAppPlan.PlanFeatureModel;

@Repository
public interface IPlanFeatureRepository extends JpaRepository<PlanFeatureModel, Integer> {

}
