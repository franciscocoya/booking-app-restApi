package com.hosting.rest.api.repositories.User.UserConfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;

@Repository
public interface IUserConfigurationRepository extends JpaRepository<UserConfigurationModel, Integer> {

}
