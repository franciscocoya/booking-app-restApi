package com.hosting.rest.api.repositories.Accomodation.SavedAccomodation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;

@Repository
public interface ISavedAccomodationRepository extends JpaRepository<SavedAccomodationModel, Integer> {

}
