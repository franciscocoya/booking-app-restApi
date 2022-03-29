package com.hosting.rest.api.services.Accomodation.AccomodationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationServiceRepository;

@Service
public class IAccomodationServiceServiceImpl implements IAccomodationServiceService {

	@Autowired
	private IAccomodationServiceRepository accomodationServiceRepo;

	@Override
	public AccomodationServiceModel addNewAccomodationService(final AccomodationServiceModel accomodationService) {
		return accomodationService != null ? accomodationServiceRepo.save(accomodationService) : null;
	}

//	@Override
//	public void deleteAccomodationServiceById(final Integer accomodationServiceId) {
//		if (accomodationServiceId != null && accomodationServiceId > 0) {
//			accomodationServiceRepo.deleteById(accomodationServiceId);
//		}
//	}

	@Override
	public AccomodationServiceModel updateAccomodationService(final AccomodationServiceModel accomodationService) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccomodationServiceModel> listAllAccomodationServices() {
		return accomodationServiceRepo.findAll();
	}

	@Override
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(final String regNumber) {
		return accomodationServiceRepo.findByAccomodationId(regNumber);
	}

//	@Override
//	public AccomodationServiceModel getAccomodationServiceById(Integer idAccomodationService) {
//		return accomodationServiceRepo.findById(idAccomodationService).get();
//	}

}
