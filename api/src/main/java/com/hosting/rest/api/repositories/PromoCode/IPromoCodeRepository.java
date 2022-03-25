package com.hosting.rest.api.repositories.PromoCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;

@Repository
public interface IPromoCodeRepository extends JpaRepository<PromoCodeModel, Integer> {

}
