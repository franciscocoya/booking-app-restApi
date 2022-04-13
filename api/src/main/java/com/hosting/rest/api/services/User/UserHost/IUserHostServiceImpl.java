package com.hosting.rest.api.services.User.UserHost;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
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

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Servicio que gestiona las acciones de un usuario host.
 *
 */
@Service
@Slf4j
public class IUserHostServiceImpl implements IUserHostService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserHostRepository userHostRepo;

	@Autowired
	private IUserRepository userRepo;

	@Transactional
	@Override
	public UserHostModel upgradeUserToUserHost(final Integer userId, final String userHostDni,
			final String userHostDirection) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario [ " + userId + " ] a añadir no es válido.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] a añadir no es válido.");
		}

		if (!isStringNotBlank(userHostDni)) {
			log.error("El DNI introducido para el host no es válido.");
			throw new IllegalArgumentsCustomException("El DNI introducido para el host no es válido.");
		}

		if (!isStringNotBlank(userHostDirection)) {
			log.error("La dirección introducida para el host no es válido.");
			throw new IllegalArgumentsCustomException("La dirección introducida para el host no es válido.");
		}

		if (!userRepo.existsById(userId)) {
			log.error("El usuario a actualizar a host no existe.");
			throw new NotFoundCustomException("El usuario a actualizar a host no existe.");
		}

		UserModel oldUser = userRepo.findById(userId).get();

		UserHostModel newUserHost = new UserHostModel();

		newUserHost.setId(userId);
		newUserHost.setName(oldUser.getName());
		newUserHost.setSurname(oldUser.getSurname());
		newUserHost.setEmail(oldUser.getEmail());
		newUserHost.setPhone(oldUser.getPhone());
		newUserHost.setPass(oldUser.getPass());
		newUserHost.setProfileImage(oldUser.getProfileImage());
		newUserHost.setDni(userHostDni);
		newUserHost.setDirection(userHostDirection);

		userRepo.deleteById(userId);

		return userHostRepo.save(newUserHost);
	}

	@Override
	public void updateUserHostById(final Integer userId, final UserHostModel userHostToUpdate) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es válido.");
		}

		if (!isNotNull(userHostToUpdate)) {
			log.error("Alguno de los valores del usuario host a crear no es válido.");
			throw new IllegalArgumentsCustomException("Alguno de los valores del usuario host a crear no es válido.");
		}

		// TODO: Actualizar

		// userHostRepo.save();
	}

	@Transactional
	@Override
	public void downgradeUserHostToUser(final Integer userId) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario host [ " + userId + " ] a eliminar no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id del usuario host [ " + userId + " ] a eliminar no es válido.");
		}

		// Comprobar que el usuario sea host

		if (!isUserAHost(userId)) {
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] no es usuario host.");
		}

		UserHostModel userToDelete = userHostRepo.findById(userId).get();
		int newUserId = (int) userRepo.count();

		// Copiar los datos del usuario host que son comunes al usuario.
		UserModel newUser = new UserModel(newUserId + 1, userToDelete.getName(), userToDelete.getSurname(),
				userToDelete.getEmail(), userToDelete.getPhone(), userToDelete.getPass(),
				userToDelete.getProfileImage(), userToDelete.getIdUserConfiguration(), userToDelete.getCreatedAt());

		userRepo.save(newUser);

		// Eliminar el usuario host
		userHostRepo.deleteById(userId);

		String updateNewUserIdQuery = "UPDATE UserModel um SET um.id = :newUserId WHERE um.id = :oldUserId";

		// Actualizar el id que tenía anteriormente el usuario
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
