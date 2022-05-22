package com.hosting.rest.api.services.User.UserHost;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.configuration.security.JwtUtils;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.User.UserHostModel;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;
import com.hosting.rest.api.repositories.User.UserHost.IUserHostRepository;
import com.hosting.rest.api.services.Accomodation.AccomodationServiceImpl;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.4
 * @apiNote Servicio que gestiona las acciones de un usuario host.
 *
 */
@Service
public class IUserHostServiceImpl implements IUserHostService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserHostRepository userHostRepo;

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private AccomodationServiceImpl accomodationService;

	@Autowired
	private AuthenticationManager authenticationManager;
	/**
	 * Actualización de los datos de un usuario "starter" a usuario host dado su id
	 * <code>userId</code>, el dni y la direccion.
	 * 
	 * @param userId
	 * @param userHostDni
	 * @param userHostDirection
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public UserHostModel upgradeUserToUserHost(final Integer userId, final String userHostDni,
			final String userHostDirection) {
		// Validar id del usuario
		validateParam(isIntegerValidAndPositive(userId), "El id del usuario [ " + userId + " ] a añadir no es válido.");

		// Validar DNI del usuario host
		validateParam(isStringNotBlank(userHostDni), "El DNI introducido para el host no es válido.");

		// Validar direccion del usuario host
		validateParam(isStringNotBlank(userHostDirection), "La dirección introducida para el host no es válido.");

		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "El usuario a actualizar a host no existe.");

//		em.createQuery("INSERT INTO UserHostModel uhm SET uhm.dni = :dni, uhm.direction = :direction WHERE uhm.id = :userId")
//				.setParameter("dni", userHostDni).setParameter("direction", userHostDirection)
//				.setParameter("userId", userId).executeUpdate();
		UserModel oldUser = userRepo.getById(userId);

		String name = oldUser.getName();
		String surname = oldUser.getSurname();
		String email = oldUser.getEmail();
		String password = oldUser.getPass();
		String phone = oldUser.getPhone();
		UserConfigurationModel userConfig = oldUser.getIdUserConfiguration();

		userRepo.deleteById(userId);

		SecurityContextHolder.clearContext();
		
		return userHostRepo.save(new UserHostModel(userId, name, surname, email, phone, userConfig, password, userHostDni,
				userHostDirection));

	}

	/**
	 * Actualización de los datos de un usuario host.
	 * 
	 * @param userId
	 * @param userHostToUpdate
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public UserHostModel updateUserHostById(final Integer userId, final UserHostModel userHostToUpdate)
			throws NumberFormatException {
		// Validar el id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id del usuario [ " + userId + " ] no es válido.");

		// Validar el usuario pasado como parametro
		validateParam(isNotNull(userHostToUpdate), "Alguno de los valores del usuario host a crear no es válido.");

		// Comprobar que existe un usuario con el id pasado como parámetro.
		validateParamNotFound(userRepo.existsById(userId),
				"No existe un usuario con id " + userId + " en la aplicación.");

		UserHostModel originalUserHost = userHostRepo.findById(userId).get();

		// Biografía
		originalUserHost
				.setBio(userHostToUpdate.getBio() == null ? originalUserHost.getBio() : userHostToUpdate.getBio());

		// Dni
		originalUserHost
				.setDni(userHostToUpdate.getDni() == null ? originalUserHost.getDni() : userHostToUpdate.getDni());

		// Dirección
		originalUserHost.setDirection(userHostToUpdate.getDirection() == null ? originalUserHost.getDirection()
				: userHostToUpdate.getDirection());

		// Email verificado
		originalUserHost.setEmailVerified(userHostToUpdate.isEmailVerified());

		// DNI verificado
		originalUserHost.setDniVerified(userHostToUpdate.isDniVerified());

		// Teléfono verificado
		originalUserHost.setPhoneVerified(userHostToUpdate.isPhoneVerified());

		return userHostRepo.save(originalUserHost);
	}

	/**
	 * Actualización de un usuario host a usuario starter (Un downgrade).
	 * 
	 * @param userId
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Transactional
	@Override
	public void downgradeUserHostToUser(final Integer userId) throws NumberFormatException {
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId),
				"El id del usuario host [ " + userId + " ] a eliminar no es válido.");

		// Comprobar que el usuario sea host
		validateParamNotFound(isUserAHost(userId), "El usuario con id [ " + userId + " ] no es usuario host.");

		// Eliminar alojamientos del usuario
		List<AccomodationModel> userAccomodations = accomodationService.findByUserId(userId);

		for (AccomodationModel acc : userAccomodations) {
			accomodationRepo.delete(acc);
		}

		// Eliminar el usuario host
		userHostRepo.deleteById(userId);

//		String updateNewUserIdQuery = "UPDATE UserModel um SET um.id = :newUserId WHERE um.id = :oldUserId";

		// Actualizar el id que tenía anteriormente el usuario
//		em.createQuery(updateNewUserIdQuery).setParameter("oldUserId", newUserId + 1).setParameter("newUserId", userId)
//				.executeUpdate();
	}

	/**
	 * Comprueba si el usuario con id <code>userId</code> es usuario host.
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.s
	 */
	private Boolean isUserAHost(final Integer userId) throws NumberFormatException {
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id del usuario no es válido.");

		String getUserByIdQuery = "SELECT COUNT(um) > 0 " + "FROM UserModel um "
				+ "WHERE TYPE(um) IN (UserHostModel) AND um.id = :userId";

		TypedQuery<Boolean> isUserAHostResult = em.createQuery(getUserByIdQuery, Boolean.class);

		isUserAHostResult.setParameter("userId", userId);

		return isUserAHostResult.getSingleResult();
	}

	/**
	 * Listado de todos los usuarios host de la aplicación.
	 * 
	 * @return
	 */
	@Override
	public List<UserHostModel> findAll() {
		return userHostRepo.findAll();
	}

}
