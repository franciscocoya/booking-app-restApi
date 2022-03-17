package com.hosting.rest.api.repositories.User;

import com.hosting.rest.api.models.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {

}
