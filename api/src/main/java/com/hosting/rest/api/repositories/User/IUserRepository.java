package com.hosting.rest.api.repositories.User;

import com.hosting.rest.api.models.User.UserModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {

	public Optional<UserModel> findByEmail(final String username);
}
