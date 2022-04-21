package com.hosting.rest.api.services.Accomodation.SavedAccomodation;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.SavedAccomodation.ISavedAccomodationRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.2
 * @apiNote Controlador para gestionar las acciones relacionadas a los
 *          alojamientos guardados.
 * 
 */
@Service
public class SavedAccomodationServiceImpl implements ISavedAccomodationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ISavedAccomodationRepository savedAccomodationRepo;

	@Autowired
	private IUserRepository userRepo;

	/**
	 * Guardado de un alojamiento.
	 * 
	 * @param accomodationToSave
	 * 
	 * @return
	 */
	@Override
	public SavedAccomodationModel addNewSavedAccomodation(final SavedAccomodationModel accomodationToSave) {
		// Validar alojamiento guardado pasado como parametro
		validateParam(isNotNull(accomodationToSave), "Alguno de los valores del alojamiento a guardar no es válido.");

		// Comprobar si existe
		validateParamNotFound(!savedAccomodationRepo.existsById(accomodationToSave.getId()),
				"Ya existe un alojamiento guardado con esos datos.");

		return savedAccomodationRepo.save(accomodationToSave);
	}

	/**
	 * Obtención del alojamiento con id <code>savedAccomodationId</code>.
	 * 
	 * @param savedAccomodationId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si es id del alojamiento guardado no es un
	 *                               número.
	 */
	@Override
	public SavedAccomodationModel getSavedAccomodationById(final Integer savedAccomodationId)
			throws NumberFormatException {

		// Validar id del alojamiento guardado
		validateParam(isIntegerValidAndPositive(savedAccomodationId), "El id del alojamiento guardado no es válido.");

		return savedAccomodationRepo.findById(savedAccomodationId).get();
	}

	/**
	 * Borrado del alojamiento con id <code>savedAccomodationId</code>.
	 * 
	 * @param savedAccomodationId
	 * 
	 * @throws NumberFormatException Si es id del alojamiento guardado no es un
	 *                               número.
	 */
	@Override
	public void deleteSavedAccomodation(final Integer savedAccomodationId) throws NumberFormatException {
		// Validar id del alojamiento guardado.
		validateParam(isIntegerValidAndPositive(savedAccomodationId),
				"El id del alojamiento guardado [ " + savedAccomodationId + " ] no es válido.");

		// Comprobar si existe el alojamiento guardado
		validateParamNotFound(savedAccomodationRepo.existsById(savedAccomodationId),
				"No existe un alojamiento guardado con id " + savedAccomodationId);

		savedAccomodationRepo.deleteById(savedAccomodationId);
	}

	/**
	 * Listado de los alojamientos guardados por el usuario con id
	 * <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del usuario no es un número.
	 */
	@Override
	public List<SavedAccomodationModel> findAllSavedAccomodationsByUserId(final Integer userId)
			throws NumberFormatException {

		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario " + userId + " no es válido.");

		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "No existe un usuario con id " + userId);

		String findAllSavedAccomodationsByUserIdQuery = "SELECT sam "
				+ "FROM SavedAccomodationModel sam INNER JOIN sam.idUser u " + "WHERE u.id = :userId";

		TypedQuery<SavedAccomodationModel> savedAccomodations = em.createQuery(findAllSavedAccomodationsByUserIdQuery,
				SavedAccomodationModel.class);

		savedAccomodations.setParameter("userId", userId);

		return savedAccomodations.getResultList();
	}

}
