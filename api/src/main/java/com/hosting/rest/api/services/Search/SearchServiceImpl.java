package com.hosting.rest.api.services.Search;

import static com.hosting.rest.api.Utils.AppUtils.isDateValid;
import static com.hosting.rest.api.Utils.AppUtils.isFirstDateLessThanSecondDate;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Search.SearchModel;
import com.hosting.rest.api.repositories.Search.ISearchRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Servicio que gestiona las búsquedas realizadas por los usuario en la
 *          aplicación.
 *
 */
@Slf4j
@Service
public class SearchServiceImpl implements ISearchService {

	@Autowired
	private ISearchRepository searchRepo;

	@Autowired
	private IUserRepository userRepo;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Añade una nueva búsqueda <code>searchToAdd</code>. Una búsqueda contiene una
	 * sentencia.
	 * 
	 * @param searchToAdd
	 * 
	 * @return Búsqueda añadida.
	 * 
	 */
	@Override
	public SearchModel addNewSearch(final SearchModel searchToAdd) {

		// Comprobar que la búsqueda a añadir no es null.
		if (!isNotNull(searchToAdd)) {
			log.error("La búsqueda a añadir no es válida.");
			throw new IllegalArgumentsCustomException("La búsqueda a añadir no es válida.");
		}

		return searchRepo.save(searchToAdd);
	}

	/**
	 * Elimina una búsqueda por su id <code>searchId</code>
	 * 
	 * @param searchId
	 * 
	 */
	@Override
	public void deleteSearchById(final Integer searchId) {
		if (!isIntegerValidAndPositive(searchId)) {
			log.error("El id de búsqueda [ " + searchId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de búsqueda [ " + searchId + " ] no es válido.");
		}

		// Comprobar que la búsqueda existe
		if (!searchRepo.existsById(searchId)) {
			throw new NotFoundCustomException("La búsqueda a eliminar no existe");
		}

		searchRepo.deleteById(searchId);
	}

	/**
	 * Lista las búsquedas realizadas por un usuario <code>userId</code>
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	@Override
	public List<SearchModel> findByUserId(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		// Comprobar que el usuario existe
		if (!userRepo.existsById(userId)) {
			log.error("El usuario a listar sus búsquedas no existe");
			throw new NotFoundCustomException("El usuario a listar sus búsquedas no existe");
		}

		String findByUserIdQuery = "SELECT sm " + "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm "
				+ "WHERE ushm.idUser.id = :userId";

		TypedQuery<SearchModel> searchsByUser = em.createQuery(findByUserIdQuery, SearchModel.class);

		searchsByUser.setParameter("userId", userId);

		return searchsByUser.getResultList();
	}

	/**
	 * Lista las últimas búsquedas realizadas por el usuario <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 */
	@Override
	public List<SearchModel> findLatestByUserId(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		// Comprobar que el usuario existe
		if (!userRepo.existsById(userId)) {
			log.error("El usuario a listar sus últimas búsquedas no existe");
			throw new NotFoundCustomException("El usuario a listar sus últimas búsquedas no existe");
		}

		// Realizar la consulta paar obtener las últimas búsquedas realizadas por el
		// usuario.
		String findLatestSearchesByUserIdQuery = "SELECT sm "
				+ "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm " + "WHERE ushm.idUser.id = :userId "
				+ "ORDER BY ushm.idUser.createdAt DESC";

		TypedQuery<SearchModel> latestSearchsByUser = em.createQuery(findLatestSearchesByUserIdQuery,
				SearchModel.class);

		latestSearchsByUser.setParameter("userId", userId);

		return latestSearchsByUser.setMaxResults(MAX_LATEST_SEARCH_RESULTS).getResultList();
	}

	/**
	 * Lista las búsquedas más frecuentes del usuario <code>userId</code>.
	 */
	@Override
	public List<SearchModel> findRepeatedSearchesByUserId(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		// Comprobar que el usuario existe.
		if (!userRepo.existsById(userId)) {
			log.error("El usuario a listar sus búsquedas más frecuentes no existe");
			throw new NotFoundCustomException("El usuario a listar sus búsquedas más frecuentes no existe");
		}

		// Obtener las búsquedas más frecuentes del usuario.
		String findLatestSearchesByUserIdQuery = "SELECT sm "
				+ "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm " + "WHERE ushm.idUser.id = :userId "
				+ "GROUP BY sm.word " + "ORDER BY ushm.idUser.createdAt DESC ";

		TypedQuery<SearchModel> moreSearchedWordsByUser = em.createQuery(findLatestSearchesByUserIdQuery,
				SearchModel.class);

		moreSearchedWordsByUser.setParameter("userId", userId);

		return moreSearchedWordsByUser.setMaxResults(MAX_REPEATED_WORDS_SEARCH_RESULTS).getResultList();
	}

	/**
	 * Lista todas las búsquedas que contengan un patrón
	 * <code>wordPatternToSearch</code> pasado como parámetro.
	 */
	@Override
	public List<SearchModel> findByPattern(final String wordPatternToSearch) {
		if (!isStringNotBlank(wordPatternToSearch)) {
			log.error("El patrón introducido está vacío o no es válido.");
			throw new IllegalArgumentsCustomException("El patrón introducido está vacío o no es válido.");
		}

		// Obtener las búsquedas que coincidan con el patrón pasado como parámetro.
		String findSearchedMatchesWithPattern = "SELECT sm " + "FROM SearchModel sm "
				+ "WHERE LOWER(sm.word) LIKE LOWER(CONCAT('%', :searchPattern, '%' ))";

		TypedQuery<SearchModel> searchesMatchWithPattern = em.createQuery(findSearchedMatchesWithPattern,
				SearchModel.class);

		searchesMatchWithPattern.setParameter("searchPattern", wordPatternToSearch);

		return searchesMatchWithPattern.getResultList();
	}

	/**
	 * Lista las búsquedas más frecuentes.
	 */
	@Override
	public List<SearchModel> findWordsMoreSearched() {
		String findMoreSearchedWordsQuery = "SELECT sm "
				+ "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm " + "GROUP BY sm.word "
				+ "ORDER BY ushm.idUser.createdAt DESC ";

		TypedQuery<SearchModel> moreSearchedWords = em.createQuery(findMoreSearchedWordsQuery, SearchModel.class);

		return moreSearchedWords.setMaxResults(MAX_SEARCHED_SEARCH_RESULTS).getResultList();
	}

	/**
	 * Lista las búsquedas más frecuentes comprendidas en un espacio temporal entre
	 * <code>dateStartToSearch</code> y <code>dateEndToSearch</code>
	 */
	@Override
	public List<SearchModel> findWordsMoreSearchedBetweenTwoDates(final LocalDateTime dateStartToSearch,
			final LocalDateTime dateEndToSearch) {

		// Comprobar que la primera fecha es válida.
		if (!isDateValid(dateStartToSearch)) {
			log.error("La primera fecha para realizar el listado de búsquedas no es válida.");
			throw new IllegalArgumentsCustomException(
					"La primera fecha para realizar el listado de búsquedas no es válida.");
		}

		// Comprobar que la segunda fecha es válida.
		if (!isDateValid(dateEndToSearch)) {
			log.error("La segunda fecha para realizar el listado de búsquedas no es válida.");
			throw new IllegalArgumentsCustomException(
					"La segunda fecha para realizar el listado de búsquedas no es válida.");
		}

		// Comprobar que la primera fecha sea anterior a la segunda.
		if (!isFirstDateLessThanSecondDate(dateStartToSearch, dateEndToSearch)) {
			log.error("La fecha " + dateStartToSearch + " tiene que ser anterior a la fecha " + dateEndToSearch);
			throw new IllegalArgumentsCustomException(
					"La fecha " + dateStartToSearch + " tiene que ser anterior a la fecha " + dateEndToSearch);
		}

		String findMoreSearchedWordsBetweenTwoDatesQuery = "SELECT sm "
				+ "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm "
				+ "WHERE ushm.idUser.createdAt BETWEEN :startDate AND :endDate " + "GROUP BY sm.word "
				+ "ORDER BY ushm.idUser.createdAt DESC ";

		TypedQuery<SearchModel> moreSearchedWordsBetweenTwoDates = em
				.createQuery(findMoreSearchedWordsBetweenTwoDatesQuery, SearchModel.class);

		moreSearchedWordsBetweenTwoDates.setParameter("startDate", dateStartToSearch);
		moreSearchedWordsBetweenTwoDates.setParameter("endDate", dateEndToSearch);

		return moreSearchedWordsBetweenTwoDates.setMaxResults(MAX_SEARCHED_SEARCH_RESULTS).getResultList();
	}

	/**
	 * Borrado del historial de búsquedas de un usuario <code>userId</code>
	 */
	@Transactional
	@Override
	public void deleteAllUserSearchHistory(final Integer userId) {
		String deleteUserSearchHistoryQuery = "DELETE FROM UserSearchHistoryModel ushm WHERE ushm.idUser.id = :userId";

		// Comprobar que existe un usuario con el el id pasado como parametro.
		if (!userRepo.existsById(userId)) {
			log.error("El usuario con id [ " + userId + " ] a eliminar su historial de búsquedas no existe.");
			throw new NotFoundCustomException(
					"El usuario con id [ " + userId + " ] a eliminar su historial de búsquedas no existe.");
		}

		Query moreSearchedWords = em.createQuery(deleteUserSearchHistoryQuery);

		moreSearchedWords.setParameter("userId", userId);

		moreSearchedWords.executeUpdate();
	}

	/**
	 * Borrado de una búsqueda <code>searchId</code> realizada por un determinado
	 * usuario <code>userId</code>.
	 */
	@Transactional
	@Override
	public void deleteUserSearchByUserId(final Integer userId, final Integer searchId) {

		// Comprobar que el id de usuario es válido.
		if (!isIntegerValidAndPositive(userId)) {
			log.error("La usuario con id [ " + searchId + " ] a eliminar la búsqueda [ " + searchId + " no es válido.");
			throw new NotFoundCustomException(
					"La usuario con id [ " + searchId + " ] a eliminar la búsqueda [ " + searchId + " no es válido.");
		}

		// Comprobar que existe un usuario con el userId.
		if (!isIntegerValidAndPositive(searchId)) {
			log.error("La búsqueda con id [ " + searchId + " ] a eliminar del historial del usuario [ " + userId
					+ " no es válida.");
			throw new NotFoundCustomException("La búsqueda con id [ " + searchId
					+ " ] a eliminar del historial del usuario [ " + userId + " no es válida.");
		}

		// Comprobar que el usuario existe
		if (!userRepo.existsById(userId)) {
			log.error("El usuario con id [ " + userId + " ] a eliminar su historial de búsquedas no existe.");
			throw new NotFoundCustomException(
					"El usuario con id [ " + userId + " ] a eliminar su historial de búsquedas no existe.");
		}

		String deleteUserSearchHistoryQuery = "DELETE FROM UserSearchHistoryModel ushm "
				+ "WHERE ushm.idUser.id = :userId AND ushm.idSearch.id = :searchId";

		Query moreSearchedWords = em.createQuery(deleteUserSearchHistoryQuery);

		moreSearchedWords.setParameter("userId", userId);
		moreSearchedWords.setParameter("searchId", searchId);

		moreSearchedWords.executeUpdate();
	}
}
