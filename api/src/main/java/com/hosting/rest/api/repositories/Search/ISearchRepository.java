package com.hosting.rest.api.repositories.Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Search.SearchModel;

@Repository
public interface ISearchRepository extends JpaRepository<SearchModel, Integer> {

}
