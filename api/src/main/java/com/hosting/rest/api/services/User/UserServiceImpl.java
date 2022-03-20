package com.hosting.rest.api.services.User;

import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepo;

    @Override
    public List<UserModel> getAllUsers() {
        return userRepo.findAll();
    }

	@Override
	public Optional<UserModel> getUserById(Integer userId) {
		return userRepo.findById(userId);
	}
}
