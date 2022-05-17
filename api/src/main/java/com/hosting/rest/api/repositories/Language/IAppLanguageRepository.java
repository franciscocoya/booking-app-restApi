package com.hosting.rest.api.repositories.Language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Language.LanguageModel;

@Repository
public interface IAppLanguageRepository extends JpaRepository<LanguageModel, Integer>{

}
