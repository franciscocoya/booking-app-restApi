package com.hosting.rest.api.services.User;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Servicio que gestiona todas las acciones de un usuario de la
 *          aplicación.
 *
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserRepository userRepo;

	@Override
	public UserModel addNewUser(final UserModel userToAdd) {

		if (!isNotNull(userToAdd)) {
			log.error("Alguna propiedad del usuario a crear falta o no es válida.");
			throw new IllegalArgumentsCustomException("Alguna propiedad del usuario a crear falta o no es válida.");
		}

		boolean existsUser = userRepo.existsById(userToAdd.getId());

		if (existsUser) {
			log.error("Ya existe un usuario en la aplicación.");
			throw new IllegalArgumentsCustomException("Ya existe un usuario en la aplicación.");
		}

		return userRepo.save(userToAdd);
	}

	/**
	 * @param userId
	 * 
	 * @return Devuelve un usuario a partir del id pasado como parámetro.
	 */
	@Override
	public UserModel getUserById(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido o está vacío.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido o está vacío.");
		}

		return userRepo.findById(userId)
				.orElseThrow(() -> new NotFoundCustomException("El usuario con id [ " + userId + " ] no existe."));
	}

	/**
	 * Actualiza los datos de un usuario.
	 * 
	 * @param userId
	 * @param userToUpdate
	 * 
	 * @return Devuelve el usuario con los datos actualizados.
	 */
	@Override
	public UserModel updateUser(final Integer userId, final UserModel userToUpdate) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		if (!isNotNull(userToUpdate)) {
			log.error("Alguna propiedad del usuario a actualizar falta o no es válida.");
			throw new IllegalArgumentsCustomException(
					"Alguna propiedad del usuario a actualizar falta o no es válida.");
		}

		boolean existsUser = userRepo.findById(userToUpdate.getId()).get() != null;

		// Comprobar que el usuario existe para poder actualizar sus datos.
		if (!existsUser) {
			log.error("El usuario con id [ " + userId + " ] no existe.");
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] no existe.");
		}

		UserModel originalUser = userRepo.findById(userId).get();

		// Datos del usuario
		originalUser.setName(userToUpdate.getName());
		originalUser.setSurname(userToUpdate.getSurname());
		originalUser.setSurname(userToUpdate.getSurname());
		originalUser.setEmail(userToUpdate.getEmail());

		return originalUser != null ? userRepo.save(originalUser) : null;
	}

	/**
	 * Elimina un usuario dado su id.
	 * 
	 * @param userId
	 */
	@Override
	public void deleteUserById(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			log.error("Alguna propiedad del usuario a crear falta o no es válida.");
			throw new IllegalArgumentsCustomException("Alguna propiedad del usuario a crear falta o no es válida.");
		}

		boolean existsUser = userRepo.findById(userId).get() != null;

		if (!existsUser) {
			log.error("No existe un usuario con id [ " + userId + " ]");
			throw new NotFoundCustomException("No existe un usuario con id [ " + userId + " ]");
		}

		userRepo.deleteById(userId);
	}

	/**
	 * Listado de los usuarios registrados (No hosts) de la aplicación.
	 */
	@Override
	public List<UserModel> findAllStartedUsers() {
		return userRepo.findAll();
	}

	/**
	 * @return Listado de todos los usuarios de la aplicación.
	 */
	@Override
	public List<UserModel> findAllUsers() {
		String findAllHostUsersQuery = "SELECT um " + " FROM UserModel um"
				+ " WHERE TYPE(um) IN (UserModel, UserHostModel)";

		TypedQuery<UserModel> allUsers = em.createQuery(findAllHostUsersQuery, UserModel.class);

		return allUsers.getResultList();
	}

	@Override
	public UserDetails loadUserByUsername(final String emailToSearch) throws UsernameNotFoundException {

		if (!isStringNotBlank(emailToSearch)) {
			log.error("El email de usuario a buscar está vacío.");
			throw new IllegalArgumentsCustomException("El email de usuario a buscar está vacío.");
		}

		UserModel user = userRepo.findByEmail(emailToSearch).orElseThrow(
				() -> new NotFoundCustomException("El usuario con email " + emailToSearch + " a cargar no existe."));

		GrantedAuthority authority = new SimpleGrantedAuthority("manager");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(),
				Arrays.asList(authority));
	}
}
