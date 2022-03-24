package com.hosting.rest.api.controllers.Accomodation;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.services.Accomodation.AccomodationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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

    @GetMapping("all")
    public List<AccomodationModel> getAllAccomodations() {
        return accomodationService.getAllAccomodations();
    }

    @GetMapping("{regNumber}")
    public AccomodationModel getAccomodationById(@PathVariable(value = "regNumber") String regNumber) {
        return accomodationService.getAccomodationById(regNumber.trim());
    }

    @GetMapping(value = "/cities/{city}")
    public List<AccomodationModel> getAccomodationsByCity(@PathParam(value = "city") String city) {
        return accomodationService.getAccomodationsByCity(city.trim());
    }

    @PostMapping("/new")
    public AccomodationModel addNewAccomodation(@RequestBody AccomodationModel accomodationModel) {
        return accomodationService.addNewAccomodation(accomodationModel);
    }

    @DeleteMapping("/regNumber")
    public void removeAccomodationById(@PathVariable(value = "regNumber") String regNumber) {
        accomodationService.removeAccomodationById(regNumber);
    }
}
