package com.hosting.rest.api.services.User.UserHost;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.User.UserHostModel;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;
import com.hosting.rest.api.repositories.User.UserHost.IUserHostRepository;

@Service
public class IUserHostServiceImpl implements IUserHostService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserHostRepository userHostRepo;

	@Autowired
	private IUserRepository userRepo;

	@Override
	public UserHostModel upgradeUserToUserHost(final Integer userId, final UserHostModel userHostToAdd) {

		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] a añadir no es válido.");
		}

		if (!isNotNull(userHostToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores introducidos para el usuario host no es válido.");
		}

		if (!userRepo.existsById(userId)) {
			throw new NotFoundCustomException("El usuario a actualizar a host no existe.");
		}

		return userHostRepo.save(userHostToAdd);
	}

	@Override
	public void updateUserHostById(final Integer userId, final UserHostModel userHostToUpdate) {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void downgradeUserHostToUser(final Integer userId) {

		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException(
					"El id del usuario host [ " + userId + " ] a eliminar no es válido.");
		}

		// Comprobar que el usuario sea host

		if (!isUserAHost(userId)) {
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] no es usuario host.");
		}

		UserHostModel userToDelete = userHostRepo.findById(userId).get();
		int newUserId = (int) userRepo.count();

		UserModel newUser = new UserModel(newUserId + 1, userToDelete.getName(), userToDelete.getSurname(),
				userToDelete.getEmail(), userToDelete.getPhone(), userToDelete.getPass(),
				userToDelete.getProfileImage(), userToDelete.getIdUserConfiguration(), userToDelete.getCreatedAt());

		userRepo.save(newUser);

		userHostRepo.deleteById(userId);

		String updateNewUserIdQuery = "UPDATE UserModel um SET um.id = :newUserId WHERE um.id = :oldUserId";

		em.createQuery(updateNewUserIdQuery).setParameter("oldUserId", newUserId + 1).setParameter("newUserId", userId)
				.executeUpdate();
	}

	/**
	 * Comprueba si el usuario con id <code>userId</code> es usuario host.
	 * 
	 * @param userId
	 * @return
	 */
	private Boolean isUserAHost(final Integer userId) {
		String getUserByIdQuery = "SELECT COUNT(um) > 0 " + "FROM UserModel um "
				+ "WHERE TYPE(um) IN (UserHostModel) AND um.id = :userId";

		TypedQuery<Boolean> isUserAHostResult = em.createQuery(getUserByIdQuery, Boolean.class);

		isUserAHostResult.setParameter("userId", userId);

		return isUserAHostResult.getSingleResult();
	}

	@Override
	public List<UserHostModel> findAll() {
		return userHostRepo.findAll();
	}

}
