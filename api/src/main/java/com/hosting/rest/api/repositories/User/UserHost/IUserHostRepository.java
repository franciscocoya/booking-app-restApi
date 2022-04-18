package com.hosting.rest.api.repositories.User.UserHost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.User.UserHostModel;

@Repository
public interface IUserHostRepository extends JpaRepository<UserHostModel, Integer> {

}
