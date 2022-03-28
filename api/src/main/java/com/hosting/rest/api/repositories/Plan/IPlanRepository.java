package com.hosting.rest.api.repositories.Plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Plan.PlanModel;

@Repository
public interface IPlanRepository extends JpaRepository<PlanModel, Integer> {

}
