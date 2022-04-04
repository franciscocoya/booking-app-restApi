package com.hosting.rest.api.services.Accomodation.AccomodationRule;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationRuleModel;

public interface IAccomodationRuleService {

	public AccomodationRuleModel addNewAccomodationRule(final AccomodationRuleModel accomodationRuleToAdd);

	public AccomodationRuleModel updateAccomodationRule(final Integer accomodationRuleId,
			final AccomodationRuleModel accomodationRuleToAdd);

	public AccomodationRuleModel findById(final Integer accomodationRuleId);

	public void deleteAccomodationRule(final Integer accomodationRuleId);

	public List<AccomodationRuleModel> findByAccomodationRegNumber(final String regNumber);
}
