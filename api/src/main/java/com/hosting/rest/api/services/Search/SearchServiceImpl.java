package com.hosting.rest.api.services.Search;

import static com.hosting.rest.api.Utils.AppUtils.isDateValid;
import static com.hosting.rest.api.Utils.AppUtils.isFirstDateLessThanSecondDate;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Search.SearchModel;
import com.hosting.rest.api.repositories.Search.ISearchRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Servicio que gestiona las búsquedas realizadas por los usuario en la
 *          aplicación.
 *
 */
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

		// Validar que la búsqueda a añadir no es null.
		validateParam(isNotNull(searchToAdd), "La búsqueda a añadir no es válida.");

		// Comprobar si existe la búsqueda
		validateParamNotFound(!searchRepo.existsById(searchToAdd.getIdSearch()), "Ya existe la búsqueda introducida.");

		return searchRepo.save(searchToAdd);
	}

	/**
	 * Elimina una búsqueda por su id <code>searchId</code>
	 * 
	 * @param searchId
	 * 
	 * @throws NumberFormatException Si el id de la búsqueda no es un número.
	 */
	@Override
	public void deleteSearchById(final Integer searchId) throws NumberFormatException {
		// Validar el id de la búsqueda
		validateParam(isIntegerValidAndPositive(searchId), "El id de búsqueda [ " + searchId + " ] no es válido.");

		// Comprobar que la búsqueda existe
		validateParamNotFound(searchRepo.existsById(searchId), "La búsqueda a eliminar no existe");

		searchRepo.deleteById(searchId);
	}

	/**
	 * Lista las búsquedas realizadas por un usuario <code>userId</code>
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public List<SearchModel> findByUserId(final Integer userId) throws NumberFormatException {
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario [ " + userId + " ] no es válido.");

		// Comprobar si el usuario existe
		validateParamNotFound(userRepo.existsById(userId), "El usuario a listar sus búsquedas no existe");

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
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 * 
	 */
	@Override
	public List<SearchModel> findLatestByUserId(final Integer userId) throws NumberFormatException {
		// Validar el id de usuario.
		validateParam(isIntegerValidAndPositive(userId), "El id del usuario [ " + userId + " ] no es válido.");

		// Comprobar que el usuario existe
		validateParamNotFound(userRepo.existsById(userId), "El usuario a listar sus últimas búsquedas no existe");

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
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public List<SearchModel> findRepeatedSearchesByUserId(final Integer userId) throws NumberFormatException {
		// Validar el id del usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario [ " + userId + " ] no es válido.");

		// Comprobar si el usuario existe.
		validateParamNotFound(userRepo.existsById(userId),
				"El usuario a listar sus búsquedas más frecuentes no existe");

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
		// Validar patrón de palabra a buscar
		validateParam(isStringNotBlank(wordPatternToSearch), "El patrón introducido está vacío o no es válido.");

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
	 * 
	 * @param dateStartToSearch
	 * @param dateEndToSearch
	 * 
	 * @return
	 */
	@Override
	public List<SearchModel> findWordsMoreSearchedBetweenTwoDates(final LocalDateTime dateStartToSearch,
			final LocalDateTime dateEndToSearch) {

		// Comprobar que la primera fecha es válida.
		validateParam(isDateValid(dateStartToSearch),
				"La primera fecha para realizar el listado de búsquedas no es válida.");

		// Comprobar que la segunda fecha es válida.
		validateParam(isDateValid(dateEndToSearch),
				"La segunda fecha para realizar el listado de búsquedas no es válida.");

		// Comprobar que la primera fecha sea anterior a la segunda.
		validateParam(isFirstDateLessThanSecondDate(dateStartToSearch, dateEndToSearch),
				"La fecha " + dateStartToSearch + " tiene que ser anterior a la fecha " + dateEndToSearch);

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
	 * 
	 * @param userId
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Transactional
	@Override
	public void deleteAllUserSearchHistory(final Integer userId) throws NumberFormatException {
		String deleteUserSearchHistoryQuery = "DELETE FROM UserSearchHistoryModel ushm WHERE ushm.idUser.id = :userId";

		// Comprobar que existe un usuario con el el id pasado como parametro.
		validateParamNotFound(userRepo.existsById(userId),
				"El usuario con id [ " + userId + " ] a eliminar su historial de búsquedas no existe.");

		Query moreSearchedWords = em.createQuery(deleteUserSearchHistoryQuery);

		moreSearchedWords.setParameter("userId", userId);

		moreSearchedWords.executeUpdate();
	}

	/**
	 * Borrado de una búsqueda <code>searchId</code> realizada por un determinado
	 * usuario <code>userId</code>.
	 * 
	 * @param userId
	 * @param searchId
	 * 
	 * @throws NumberFormatException Si el id de usuario o el id de búsqueda no son
	 *                               un número.
	 */
	@Transactional
	@Override
	public void deleteUserSearchByUserId(final Integer userId, final Integer searchId) throws NumberFormatException {

		// Comprobar que el id de usuario es válido.
		validateParam(isIntegerValidAndPositive(userId),
				"La usuario con id [ " + searchId + " ] a eliminar la búsqueda [ " + searchId + " no es válido.");

		// Comprobar que existe un usuario con el userId.
		validateParam(isIntegerValidAndPositive(searchId), "La búsqueda con id [ " + searchId
				+ " ] a eliminar del historial del usuario [ " + userId + " no es válida.");

		// Comprobar que el usuario existe
		validateParamNotFound(userRepo.existsById(userId),
				"El usuario con id [ " + userId + " ] a eliminar su historial de búsquedas no existe.");

		String deleteUserSearchHistoryQuery = "DELETE FROM UserSearchHistoryModel ushm "
				+ "WHERE ushm.idUser.id = :userId AND ushm.idSearch.id = :searchId";

		Query moreSearchedWords = em.createQuery(deleteUserSearchHistoryQuery);

		moreSearchedWords.setParameter("userId", userId);
		moreSearchedWords.setParameter("searchId", searchId);

		moreSearchedWords.executeUpdate();
	}
}
