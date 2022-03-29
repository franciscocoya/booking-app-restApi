package com.hosting.rest.api.services.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.User.UserNotFoundException;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserRepository userRepo;

	@Override
	public List<UserModel> findAllStartedUsers() {
		return userRepo.findAll();
	}

	@Override
	public UserModel getUserById(Integer userId) {
		return userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

	@Override
	public List<UserModel> findAllUsers() {
		// TODO:
		String findAllHostUsersQuery = "select um" + " from UserModel um, UserHostModel uhm"
				+ " where um.id = uhm.id";

		TypedQuery<UserModel> allUsers = em.createQuery(findAllHostUsersQuery, UserModel.class);

		return allUsers.getResultList();
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
