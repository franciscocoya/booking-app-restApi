package com.hosting.rest.api.services.Accomodation.AccomodationRule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationRuleModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationRule.IAccomodationRuleRepository;

@Service
public class AccomodationRuleServiceImpl implements IAccomodationRuleService {

	@Autowired
	private IAccomodationRuleRepository accomodationRuleRepo;

	@Override
	public AccomodationRuleModel addNewAccomodationRule(final AccomodationRuleModel accomodationRuleToAdd) {
		if (!isNotNull(accomodationRuleToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la norma del alojamiento a añadir no es válido.");
		}

		// Comprobar que la norma a añadir no existe
		boolean existsAccomodationRule = accomodationRuleRepo.existsById(accomodationRuleToAdd.getId());

		if(existsAccomodationRule) {
			throw new IllegalArgumentsCustomException("");
		}
		
		return null;
	}

	@Override
	public AccomodationRuleModel updateAccomodationRule(final Integer accomodationRuleId,
			final AccomodationRuleModel accomodationRuleToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccomodationRuleModel findById(final Integer accomodationRuleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccomodationRule(final Integer accomodationRuleId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AccomodationRuleModel> findByAccomodationRegNumber(final String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
