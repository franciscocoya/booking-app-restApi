package com.hosting.rest.api.repositories.Plan.PlanSubscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Plan.PlanSubscriptionModel;
import com.hosting.rest.api.models.Plan.PlanSubscriptionUserHostId;

@Repository
public interface IPlanSubscriptionRepository extends JpaRepository<PlanSubscriptionModel, PlanSubscriptionUserHostId> {

}
