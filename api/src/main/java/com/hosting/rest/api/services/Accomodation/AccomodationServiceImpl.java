package com.hosting.rest.api.services.Accomodation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.AccomodationNotFoundException;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Implementa los métodos de los servicios de un alojamiento.
 **/
@Service
public class AccomodationServiceImpl implements IAccomodationService {

    @Autowired
    private IAccomodationRepository accomodationRepo;

    @Override
    public AccomodationModel addNewAccomodation(AccomodationModel accomodationModel) {
        return accomodationRepo.save(accomodationModel);
    }

    @Override
    public List<AccomodationModel> listAllAccomodations(){
        return accomodationRepo.findAll();
    }

    @Override
    public AccomodationModel getAccomodationById(String regNumber) {
        return accomodationRepo.findById(regNumber)
                .orElseThrow(() -> new AccomodationNotFoundException(regNumber));
    }

    @Override
    public void removeAccomodationById(String regNumber) {
        accomodationRepo.deleteById(regNumber);
    }

	@Override
	public List<AccomodationModel> listAccomodationsByCity(String cityToSearch) {
		return accomodationRepo.findAll();
	}
}
