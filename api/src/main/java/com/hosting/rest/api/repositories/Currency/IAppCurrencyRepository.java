package com.hosting.rest.api.repositories.Currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Currency.CurrencyModel;

@Repository
public interface IAppCurrencyRepository extends JpaRepository<CurrencyModel, Integer> {

}
