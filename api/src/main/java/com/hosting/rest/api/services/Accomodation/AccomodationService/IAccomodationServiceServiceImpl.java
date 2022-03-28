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
	public AccomodationServiceModel addNewAccomodationService(AccomodationServiceModel accomodationService) {
		return accomodationServiceRepo.save(accomodationService);
	}

	@Override
	public void deleteAccomodationServiceById(Integer accomodationServiceId) {
		accomodationServiceRepo.deleteById(accomodationServiceId);
	}

	@Override
	public AccomodationServiceModel updateAccomodationService(AccomodationServiceModel accomodationService) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccomodationServiceModel> listAllAccomodationServices() {
		return accomodationServiceRepo.findAll();
	}

	@Override
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccomodationServiceModel getAccomodationServiceById(Integer accomodationServiceId) {
		return accomodationServiceRepo.findById(accomodationServiceId).get();
	}
	
}
