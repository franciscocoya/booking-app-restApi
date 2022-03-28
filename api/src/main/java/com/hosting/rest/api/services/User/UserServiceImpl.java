package com.hosting.rest.api.services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.User.UserNotFoundException;
import com.hosting.rest.api.models.User.UserHostModel;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepo;

	@Override
	public List<UserModel> listAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public UserModel getUserById(Integer userId) {
		return userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

	@Override
	public List<UserHostModel> listHostUsers() {
		return null;
	}

	@Override
	public boolean isUserHostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserModel addNewUser(UserModel userToAdd) {
		return userRepo.save(userToAdd);
	}

	@Override
	public UserModel updateUser(Integer userId, UserModel userToUpdate) {
		return null;
//		return userRepo.save(originalUser.get());
	}

	@Override
	public void deleteUserById(Integer userId) {
		userRepo.deleteById(userId);		
	}

}
