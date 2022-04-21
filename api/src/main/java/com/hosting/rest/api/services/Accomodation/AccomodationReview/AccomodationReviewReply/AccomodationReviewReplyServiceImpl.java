package com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewReply;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply.AccomodationReviewReplyModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationReview.AccomodationReviewReply.IAccomodationReviewReplyRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.4
 * @description Servicio que implementa las funcionalidades de las respuestas a
 *              las valoraciones de una alojamiento.
 * 
 **/
@Service
public class AccomodationReviewReplyServiceImpl implements IAccomodationReviewReplyService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationReviewReplyRepository accomodationReviewReplyRepo;

	/**
	 * Creación de una nueva respuesta a valoración de alojamiento.
	 * 
	 * @param accomodationReviewReplyToAdd
	 * 
	 * @return
	 */
	@Override
	public AccomodationReviewReplyModel addNewAccomodationReviewReply(
			final AccomodationReviewReplyModel accomodationReviewReplyToAdd) {
		// Validar respuesta a valoración de alojamiento pasada como parámetro.
		validateParam(isNotNull(accomodationReviewReplyToAdd),
				"La respuesta a la valoración del alojamiento no es válida.");

		// Comprobar si existe la respuesta a la valoración del alojamiento
		validateParamNotFound(
				accomodationReviewReplyRepo.existsById(accomodationReviewReplyToAdd.getIdAccomodationReviewReply()),
				"La respuesta a la valoración del alojamiento ya existe");

		return accomodationReviewReplyRepo.save(accomodationReviewReplyToAdd);
	}

	/**
	 * Borrado de la respuesta a una valoración de alojamiento con id
	 * <code>accomodationReviewId</code>.
	 * 
	 * @param accomodationReviewId
	 * 
	 * @throws NumberFormatException Si el id de la valoración no es un número.
	 */
	@Override
	public void deleteAccomodationReviewReplyById(final Integer accomodationReviewId) throws NumberFormatException {
		// Validar el id de valoracion
		validateParam(isIntegerValidAndPositive(accomodationReviewId),
				"El id de la valoración del alojamiento no es válido.");

		// Obtención de la repuesta a partir del id de la valoración.
		AccomodationReviewReplyModel accomodationReviewReplyDeleted = findByReviewId(accomodationReviewId);

		// Comprobar si existe la respuesta de valoración.
		validateParamNotFound(isNotNull(accomodationReviewReplyDeleted), "La respuesta a eliminar no existe");

		accomodationReviewReplyRepo.delete(accomodationReviewReplyDeleted);
	}

	/**
	 * Buscar la de valoración de la valoración con id
	 * <code>accomodationReviewId</code>.
	 * 
	 * @param accomodationReviewId
	 * 
	 * @throws NumberFormatException Si el id de la valoración no es un número.
	 */
	@Override
	public AccomodationReviewReplyModel findByReviewId(final Integer accomodationReviewId)
			throws NumberFormatException {
		String findByReviewIdQuery = "SELECT arrm "
				+ "FROM AccomodationReviewReplyModel arrm INNER JOIN arrm.accomodationReview arm "
				+ "WHERE arm.id = :accomodationReviewId";

		TypedQuery<AccomodationReviewReplyModel> accomodationReviewReply = em.createQuery(findByReviewIdQuery,
				AccomodationReviewReplyModel.class);

		accomodationReviewReply.setParameter("accomodationReviewId", accomodationReviewId);

		return accomodationReviewReply.getSingleResult();
	}

}
