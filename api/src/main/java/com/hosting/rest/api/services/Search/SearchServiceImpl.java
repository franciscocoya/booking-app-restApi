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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Search.SearchModel;
import com.hosting.rest.api.repositories.Search.ISearchRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchServiceImpl implements ISearchService {
	
//	private static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Autowired
	private ISearchRepository searchRepo;

	@Autowired
	private IUserRepository userRepo;

	@PersistenceContext
	private EntityManager em;

	@Override
	public SearchModel addNewSearch(final SearchModel searchToAdd) {

		if (!isNotNull(searchToAdd)) {
			log.error("La búsqueda a añadir no es válida.");
			throw new IllegalArgumentsCustomException("La búsqueda a añadir no es válida.");
		}

		return searchRepo.save(searchToAdd);
	}

	@Override
	public void deleteSearchById(final Integer searchId) {
		if (!isIntegerValidAndPositive(searchId)) {
			throw new IllegalArgumentsCustomException("El id de búsqueda [ " + searchId + " ] no es válido.");
		}

		if (!searchRepo.existsById(searchId)) {
			throw new NotFoundCustomException("La búsqueda a eliminar no existe");
		}

		searchRepo.deleteById(searchId);
	}

	@Override
	public List<SearchModel> findByUserId(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		if (!userRepo.existsById(userId)) {
			throw new NotFoundCustomException("El usuario a listar sus búsquedas no existe");
		}

		String findByUserIdQuery = "SELECT sm " + "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm "
				+ "WHERE ushm.idUser.id = :userId";

		TypedQuery<SearchModel> searchsByUser = em.createQuery(findByUserIdQuery, SearchModel.class);

		searchsByUser.setParameter("userId", userId);

		return searchsByUser.getResultList();
	}

	@Override
	public List<SearchModel> findLatestByUserId(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		if (!userRepo.existsById(userId)) {
			throw new NotFoundCustomException("El usuario a listar sus últimas búsquedas no existe");
		}

		String findLatestSearchesByUserIdQuery = "SELECT sm "
				+ "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm " + "WHERE ushm.idUser.id = :userId "
				+ "ORDER BY ushm.idUser.createdAt DESC";

		TypedQuery<SearchModel> latestSearchsByUser = em.createQuery(findLatestSearchesByUserIdQuery,
				SearchModel.class);

		latestSearchsByUser.setParameter("userId", userId);

		return latestSearchsByUser.setMaxResults(MAX_LATEST_SEARCH_RESULTS).getResultList();
	}

	@Override
	public List<SearchModel> findRepeatedSearchesByUserId(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		if (!userRepo.existsById(userId)) {
			throw new NotFoundCustomException("El usuario a listar sus búsquedas más frecuentes no existe");
		}

		String findLatestSearchesByUserIdQuery = "SELECT sm "
				+ "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm " + "WHERE ushm.idUser.id = :userId "
				+ "GROUP BY sm.word " + "ORDER BY ushm.idUser.createdAt DESC ";

		TypedQuery<SearchModel> moreSearchedWordsByUser = em.createQuery(findLatestSearchesByUserIdQuery,
				SearchModel.class);

		moreSearchedWordsByUser.setParameter("userId", userId);

		return moreSearchedWordsByUser.setMaxResults(MAX_REPEATED_WORDS_SEARCH_RESULTS).getResultList();
	}

	@Override
	public List<SearchModel> findByPattern(final String wordPatternToSearch) {
		if (!isStringNotBlank(wordPatternToSearch)) {
			throw new IllegalArgumentsCustomException("El patrón introducido está vacío o no es válido.");
		}

		String findSearchedMatchesWithPattern = "SELECT sm " + "FROM SearchModel sm "
				+ "WHERE LOWER(sm.word) LIKE LOWER(CONCAT('%', :searchPattern, '%' ))";

		TypedQuery<SearchModel> searchesMatchWithPattern = em.createQuery(findSearchedMatchesWithPattern,
				SearchModel.class);

		searchesMatchWithPattern.setParameter("searchPattern", wordPatternToSearch);

		return searchesMatchWithPattern.getResultList();
	}

	@Override
	public List<SearchModel> findWordsMoreSearched() {
		String findMoreSearchedWordsQuery = "SELECT sm "
				+ "FROM UserSearchHistoryModel ushm INNER JOIN ushm.idSearch sm " + "GROUP BY sm.word "
				+ "ORDER BY ushm.idUser.createdAt DESC ";

		TypedQuery<SearchModel> moreSearchedWords = em.createQuery(findMoreSearchedWordsQuery, SearchModel.class);

		return moreSearchedWords.setMaxResults(MAX_SEARCHED_SEARCH_RESULTS).getResultList();
	}

	@Override
	public List<SearchModel> findWordsMoreSearchedBetweenTwoDates(final LocalDateTime dateStartToSearch,
			final LocalDateTime dateEndToSearch) {

		if (!isDateValid(dateStartToSearch)) {
			throw new IllegalArgumentsCustomException(
					"La primera fecha para realizar el listado de búsquedas no es válida.");
		}

		if (!isDateValid(dateEndToSearch)) {
			throw new IllegalArgumentsCustomException(
					"La segunda fecha para realizar el listado de búsquedas no es válida.");
		}

		if (!isFirstDateLessThanSecondDate(dateStartToSearch, dateEndToSearch)) {
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

	@Transactional
	@Override
	public void deleteAllUserSearchHistory(final Integer userId) {
		String deleteUserSearchHistoryQuery = "DELETE FROM UserSearchHistoryModel ushm WHERE ushm.idUser.id = :userId";

		if (!userRepo.existsById(userId)) {
			throw new NotFoundCustomException(
					"El usuario con id [ " + userId + " ] a eliminar su historial de búsquedas no existe.");
		}

		Query moreSearchedWords = em.createQuery(deleteUserSearchHistoryQuery);

		moreSearchedWords.setParameter("userId", userId);

		moreSearchedWords.executeUpdate();
	}

	@Transactional
	@Override
	public void deleteUserSearchByUserId(final Integer userId, final Integer searchId) {
		if (!isIntegerValidAndPositive(userId)) {
			throw new NotFoundCustomException(
					"La usuario con id [ " + searchId + " ] a eliminar la búsqueda [ " + searchId + " no es válido.");
		}

		if (!isIntegerValidAndPositive(searchId)) {
			throw new NotFoundCustomException("La búsqueda con id [ " + searchId
					+ " ] a eliminar del historial del usuario [ " + userId + " no es válida.");
		}

		if (!userRepo.existsById(userId)) {
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
