package com.hosting.rest.api.services.Search;

import java.time.LocalDateTime;
import java.util.List;

import com.hosting.rest.api.models.Search.SearchModel;

public interface ISearchService {

	/**
	 * Número máximo de resultados de búsqueda a mostrar.
	 */
	public static final int MAX_LATEST_SEARCH_RESULTS = 4;
	public static final int MAX_REPEATED_WORDS_SEARCH_RESULTS = 4;
	public static final int MAX_SEARCHED_SEARCH_RESULTS = 3;

	public SearchModel addNewSearch(final SearchModel searchToAdd);

	public void deleteSearchById(final Integer searchId);

	public List<SearchModel> findByUserId(final Integer userId);

	public List<SearchModel> findLatestByUserId(final Integer userId);

	public List<SearchModel> findRepeatedSearchesByUserId(final Integer userId);

	public void deleteUserSearchByUserId(final Integer userId, final Integer searchId);

	public void deleteAllUserSearchHistory(final Integer userId);

	public List<SearchModel> findByPattern(final String wordPatternToSearch);

	public List<SearchModel> findWordsMoreSearched();

	public List<SearchModel> findWordsMoreSearchedBetweenTwoDates(final LocalDateTime dateStartToSearch,
			final LocalDateTime dateEndToSearch);
}
