package com.hosting.rest.api.controllers.Accomodation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.services.Accomodation.AccomodationServiceImpl;

/**
 * @author Francisco Coya Abajo
 * @version v1.0.0
 * @apiNote Controlador de alojamientos.
 */
@RestController
@RequestMapping("/accomodations")
public class AccomodationController {

    @Autowired
    private AccomodationServiceImpl accomodationService;

    @GetMapping(value = "all")
    public List<AccomodationModel> getAllAccomodations() {
        return accomodationService.listAllAccomodations();
    }

    @GetMapping("{regNumber}")
    public AccomodationModel getAccomodationById(@PathVariable(value = "regNumber") String regNumber) {
        return accomodationService.getAccomodationById(regNumber.trim());
    }

    @GetMapping("cities/{city}")
    public List<AccomodationModel> getAccomodationsByCity(@PathVariable(value = "city") String city) {
        return accomodationService.listAccomodationsByCity(city.trim());
    }

    @PostMapping("new")
    public AccomodationModel addNewAccomodation(@RequestBody AccomodationModel accomodationModel) {
        return accomodationService.addNewAccomodation(accomodationModel);
    }

    @DeleteMapping("{regNumber}")
    public void removeAccomodationById(@PathVariable(value = "regNumber") String regNumber) {
        accomodationService.removeAccomodationById(regNumber);
    }
   
    
    // TODO: Obtener las valoraciones de un alojamiento.
    
    // TODO: Obtener la valoracion media de un alojamiento.
    
    // TODO: Obtener los 4 últimos usuarios que han valorado el alojamiento.
       
    
}
