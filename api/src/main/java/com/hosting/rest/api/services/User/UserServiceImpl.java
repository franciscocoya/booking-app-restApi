package com.hosting.rest.api.services.User;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;
import com.hosting.rest.api.services.UserDetails.UserDetailsImpl;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.4
 * @apiNote Servicio que gestiona todas las acciones de un usuario de la
 *          aplicación.
 *
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserRepository userRepo;

	/**
	 * @param userId
	 * 
	 * @return Devuelve un usuario a partir del id pasado como parámetro.
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public UserModel getUserById(final Integer userId) throws NumberFormatException {
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId),
				"El id de usuario [ " + userId + " ] no es válido o está vacío.");

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
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario [ " + userId + " ] no es válido.");

		// Validar usuario a actualizar
		validateParam(isNotNull(userToUpdate), "Alguna propiedad del usuario a actualizar falta o no es válida.");

		// Comprobar que el usuario existe para poder actualizar sus datos.
		validateParamNotFound(userRepo.existsById(userId), "El usuario con id [ " + userId + " ] no existe.");

		UserModel originalUser = userRepo.findById(userId).get();

		// Datos del usuario
		originalUser.setName(userToUpdate.getName());
		originalUser.setSurname(userToUpdate.getSurname());
		originalUser.setPhone(userToUpdate.getPhone());
		originalUser.setEmail(userToUpdate.getEmail());

		return originalUser != null ? userRepo.save(originalUser) : null;
	}

	/**
	 * Elimina un usuario dado su id.
	 * 
	 * @param userId
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public void deleteUserById(final Integer userId) throws NumberFormatException {
		// Validar el id del usuario
		validateParam(isIntegerValidAndPositive(userId), "Alguna propiedad del usuario a crear falta o no es válida.");

		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "No existe un usuario con id [ " + userId + " ]");

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

	/**
	 * Comprueba que existe un usuario con el email <code>emailToSearch</code> y
	 * devuelve sus credenciales de acceso.
	 * 
	 * @see #build
	 * 
	 * @param emailToSearch
	 * 
	 * @return
	 * 
	 * @throws UsernameNotFoundException Si no se encuentra el usuario con el email
	 *                                   pasado como parámetro.
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(final String emailToSearch) throws UsernameNotFoundException {
		// Validar email
		validateParam(isStringNotBlank(emailToSearch), "El email de usuario a buscar está vacío.");

		// Obtener el usuario con el email
		UserModel user = userRepo.findByEmail(emailToSearch).orElseThrow(
				() -> new NotFoundCustomException("El usuario con email " + emailToSearch + " a cargar no existe."));

		return UserDetailsImpl.build(user);
	}

	/**
	 * Actualización de la imagen de perfil del usuario con id <code>userId</code>.
	 * 
	 * @param userId
	 * @param imgUrl
	 * 
	 * 
	 */
	@Override
	@Transactional
	public void updateProfileImage(final Integer userId, final String imgUrl) 
	throws NumberFormatException{
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario no es válido.");
		
		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "No existe el usuario con id " + userId);
		
		// Validar imagen
		validateParam(isStringNotBlank(imgUrl), "La imagen seleccionada no es válida.");
		
		em.createQuery("UPDATE UserModel um SET um.profileImage = :newImg WHERE um.id = :userId")
		.setParameter("newImg", imgUrl)
		.setParameter("userId", userId)
		.executeUpdate();
	}

	@Override
	public Integer getUserIdByEmail(final String email) {
		String findUserIdByEmail = "SELECT um.id " + " FROM UserModel um"
				+ " WHERE TYPE(um) IN (UserModel, UserHostModel) AND um.email = :email";

		TypedQuery<Integer> allUsers = em.createQuery(findUserIdByEmail, Integer.class)
				.setParameter("email", email);

		return allUsers.getSingleResult();
	}
}
