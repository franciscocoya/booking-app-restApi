package com.hosting.rest.api.services.Accomodation.AccomodationCategory;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.AccomodationCategoryModel;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationCategory.IAccomodationCategoryRepository;

@Service
public class AccomodationCategoryServiceImpl implements IAccomodationCategoryService {

	@Autowired
	private IAccomodationCategoryRepository accomodationCategoryRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	/**
	 * Crea una nueva categoría de alojamiento.
	 * 
	 * @param accomodationToAdd
	 * 
	 * @return
	 */
	@Override
	public AccomodationCategoryModel addNewAccomodationCategory(final AccomodationCategoryModel accomodationToAdd) {
		// Validar categoría a añadir
		validateParam(isNotNull(accomodationToAdd),
				"Alguno de los valores introducidos para la categoría no es válido.");

		// Comprobar si existe la categoría
		validateParamNotFound(!accomodationCategoryRepo.existsById(accomodationToAdd.getId()),
				"La categoría a añadir ya existe.");

		return accomodationCategoryRepo.save(accomodationToAdd);
	}

	/**
	 * Actualiza los datos de la categoría con id
	 * <code>accomodationCategoryId</code>.
	 * 
	 * @param accomodationCategoryId
	 * @param accomodationCategoryToUpdate
	 * 
	 * @return
	 */
	@Override
	public AccomodationCategoryModel updateAccomodationCategory(final Integer accomodationCategoryId,
			final AccomodationCategoryModel accomodationCategoryToUpdate) {

		// Validar id de la categoría
		validateParam(isIntegerValidAndPositive(accomodationCategoryId),
				"El id de categoría introducido no es válido.");

		// Validar datos de la categoría actualizada
		validateParam(isNotNull(accomodationCategoryToUpdate),
				"Los datos de la categoría introducidos no son válidos.");

		// Comprobar si existe la categoría
		validateParamNotFound(accomodationCategoryRepo.existsById(accomodationCategoryId),
				"La categoría a actualizar no existe.");

		AccomodationCategoryModel accomodationCategoryUpdated = accomodationCategoryRepo
				.findById(accomodationCategoryId).get();

		// Actualizar la categoria
		accomodationCategoryUpdated.setAccomodationCategory(accomodationCategoryToUpdate.getAccomodationCategory());

		return accomodationCategoryRepo.save(accomodationCategoryUpdated);
	}

	/**
	 * Elimina la categoría con id <code>accomodationCategoryId</code>.
	 * 
	 * @param
	 */
	@Override
	public void deleteAccomodationCategoryById(final Integer accomodationCategoryId) {
		// Validar id de la categoria
		validateParam(isIntegerValidAndPositive(accomodationCategoryId),
				"El id de categoría introducido no es válido.");

		// Comprobar si existe la categoría
		validateParamNotFound(accomodationCategoryRepo.existsById(accomodationCategoryId),
				"La categoría a actualizar no existe.");

		accomodationCategoryRepo.deleteById(accomodationCategoryId);
	}

	/**
	 * Lista todas las categorías de alojamiento disponibles.
	 */
	@Override
	public List<AccomodationCategoryModel> findAllAccomodationCategories() {
		return accomodationCategoryRepo.findAll();
	}

	/**
	 * Actualización de la categoría del alojamiento con número de registro
	 * <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * @param accomodationToUpdate
	 */
	@Override
	public void updateAccomodationCategoryOfAccomodation(final String regNumber,
			final AccomodationCategoryModel accomodationToUpdate) {

		// Validar número de registro
		validateParam(isStringNotBlank(regNumber), "El número de registro no puede estar vacío.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe un alojamiento con el número de registro " + regNumber);

		// Validar categoría a actualizar
		validateParam(isNotNull(accomodationToUpdate),
				"Alguno de los datos introducidos para la categoría no es válido.");

		// Comprobar si existe la categoría
		validateParamNotFound(accomodationCategoryRepo.existsById(accomodationToUpdate.getId()),
				"La categoría a actualizar no existe");

		AccomodationModel accomodationUpdated = accomodationRepo.findById(regNumber).get();

		// Actualizar categoría del alojamiento
		accomodationUpdated.setIdAccomodationCategory(accomodationToUpdate);

		accomodationRepo.save(accomodationUpdated);
	}

}
