package com.hosting.rest.api.repositories.Accomodation.AccomodationRule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationRuleModel;

@Repository
public interface IAccomodationRuleRepository extends JpaRepository<AccomodationRuleModel, Integer> {

}
