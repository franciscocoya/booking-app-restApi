package com.hosting.rest.api.services.Accomodation;

import com.hosting.rest.api.exceptions.Accomodation.AccomodationNotFoundException;
import com.hosting.rest.api.models.Accomodation.AccomodationLocationModel;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.List;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
@Service
public abstract class AccomodationServiceImpl implements IAccomodationService {

    @Autowired
    private IAccomodationRepository accomodationRepo;

    @Override
    public AccomodationModel addNewAccomodation(AccomodationModel accomodationModel) {
        return accomodationRepo.save(accomodationModel);
    }

    @Override
    public List<AccomodationModel> getAllAccomodations(){
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
}
