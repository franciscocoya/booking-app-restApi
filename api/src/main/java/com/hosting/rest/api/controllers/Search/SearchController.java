package com.hosting.rest.api.controllers.Search;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Search.SearchModel;
import com.hosting.rest.api.services.Search.SearchServiceImpl;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchServiceImpl searchService;

	@PostMapping("new")
	public SearchModel addNewSearch(@Valid @RequestBody final SearchModel searchToAdd) {
		return searchService.addNewSearch(searchToAdd);
	}

	@DeleteMapping("{searchId}")
	public void deleteSearchById(@PathVariable(value = "searchId") final String searchId) {

		try {
			searchService.deleteSearchById(Integer.parseInt(searchId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id de la búsqueda [ " + searchId + " ] no es un número.");
		}
	}

	@GetMapping("u/{userId}")
	public List<SearchModel> findByUserId(@PathVariable(value = "userId") final String userId) {
		List<SearchModel> userSearchs = null;

		try {
			userSearchs = searchService.findByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id del usuario [ " + userId + " ] a listar sus búsquedas no es un número.");
		}

		return userSearchs;
	}

	@DeleteMapping("u/{userId}")
	public void deleteUserSearchHistoryByUserId(@PathVariable(value = "userId") final String userId) {
		try {
			searchService.deleteAllUserSearchHistory(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id del usuario [ " + userId + " ] a listar sus búsquedas no es un número.");
		}
	}

	@DeleteMapping("u/{userId}/{searchId}")
	public void deleteUserSearchHistoryByUserId(@PathVariable(value = "userId") final String userId,
			@PathVariable(value = "searchId") final String searchId) {
		try {
			searchService.deleteUserSearchByUserId(Integer.parseInt(userId), Integer.parseInt(searchId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id del usuario [ " + userId + " ] a listar sus búsquedas no es un número.");
		}
	}

	@GetMapping("u/{userId}/latest")
	public List<SearchModel> findLatestSearchesByUserId(@PathVariable(value = "userId") final String userId) {
		List<SearchModel> userLatestSearchs = null;

		try {
			userLatestSearchs = searchService.findLatestByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id del usuario [ " + userId + " ] a listar sus últimas búsquedas no es un número.");
		}

		return userLatestSearchs;
	}

	@GetMapping("u/{userId}/moreSearched")
	public List<SearchModel> findMoreReatedWordsByUser(@PathVariable(value = "userId") final String userId) {
		List<SearchModel> userMoreRepeatedSearchs = null;

		try {
			userMoreRepeatedSearchs = searchService.findRepeatedSearchesByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id del usuario [ " + userId + " ] a listar sus búsquedas más frecuentes no es un número.");
		}

		return userMoreRepeatedSearchs;
	}

	@GetMapping("/q")
	public List<SearchModel> findByPattern(@RequestParam(name = "word") final String searchPatter) {
		return searchService.findByPattern(searchPatter);
	}

	@GetMapping("/moreSearched/all")
	public List<SearchModel> findMoreSearched() {
		return searchService.findWordsMoreSearched();
	}

	@GetMapping("/moreSearched")
	public List<SearchModel> findMoreSearchedBetweenTwoDates(
			@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime startDateToSearch,
			@RequestParam(value = "finish") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime finishDateToSearch) {

		return searchService.findWordsMoreSearchedBetweenTwoDates(startDateToSearch, finishDateToSearch);
	}

}
