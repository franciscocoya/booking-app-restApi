package com.hosting.rest.api.services.User;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public UserModel getUserById(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido o está vacío.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido o está vacío.");
		}

		return userRepo.findById(userId)
				.orElseThrow(() -> new NotFoundCustomException("El usuario con id [ " + userId + " ] no existe."));
	}

	@Override
	public List<UserModel> findAllUsers() {
		String findAllHostUsersQuery = "SELECT um " + " FROM UserModel um"
				+ " WHERE TYPE(um) IN (UserModel, UserHostModel)";

		TypedQuery<UserModel> allUsers = em.createQuery(findAllHostUsersQuery, UserModel.class);

		return allUsers.getResultList();
	}

	@Override
	public UserModel addNewUser(final UserModel userToAdd) {

		if (!isNotNull(userToAdd)) {
			throw new IllegalArgumentsCustomException("Alguna propiedad del usuario a crear falta o no es válida.");
		}

		boolean existsUser = userRepo.existsById(userToAdd.getId());

		if (existsUser) {
			throw new IllegalArgumentsCustomException("Ya existe un usuario en la aplicación.");
		}

		return userRepo.save(userToAdd);
	}

	@Override
	public UserModel updateUser(final Integer userId, final UserModel userToUpdate) {

		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		if (!isNotNull(userToUpdate)) {
			throw new IllegalArgumentsCustomException(
					"Alguna propiedad del usuario a actualizar falta o no es válida.");
		}

		boolean existsUser = userRepo.findById(userToUpdate.getId()).get() != null;

		if (!existsUser) {
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] no existe.");
		}

		UserModel originalUser = userRepo.findById(userId).get();

		// Nombre del usuario
		originalUser.setName(userToUpdate.getName());

		// Apellidos del usuario
		originalUser.setSurname(userToUpdate.getSurname());

		// Número de teléfono
		originalUser.setSurname(userToUpdate.getSurname());

		// Email
		originalUser.setEmail(userToUpdate.getEmail());

		return originalUser != null ? userRepo.save(originalUser) : null;
	}

	@Override
	public void deleteUserById(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("Alguna propiedad del usuario a crear falta o no es válida.");
		}

		boolean existsUser = userRepo.findById(userId).get() != null;

		if (!existsUser) {
			throw new NotFoundCustomException("No existe un usuario con id [ " + userId + " ]");
		}

		userRepo.deleteById(userId);
	}
}
