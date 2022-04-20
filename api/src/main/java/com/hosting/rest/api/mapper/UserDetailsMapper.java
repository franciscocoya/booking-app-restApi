package com.hosting.rest.api.mapper;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.controllers.User.UserRole.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hosting.rest.api.models.User.UserHostModel;
import com.hosting.rest.api.models.User.UserModel;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Detalles de un usuario.
 *
 */
public class UserDetailsMapper {

	/**
	 * 
	 * @param user
	 * 
	 * @return Devuelve un objetos UserDetails con el email, contraseña y las
	 *         authorities.
	 */
	public static UserDetails build(final UserModel user) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(),
				getAuthorities(user));
	}

	/**
	 * 
	 * @param user
	 * 
	 * @return Devuelve una lista de authorities generadas a partir de los roles del
	 *         usuario <code>user</code>.
	 */
	public static List<SimpleGrantedAuthority> getAuthorities(final UserModel user) {
		String userRoles = ROLE_ADMIN_USER.toString();

		if (isNotNull(user)) {
			userRoles = user instanceof UserHostModel ? ROLE_HOST_USER.toString() : ROLE_BASE_USER.toString();
		}

		return Arrays.asList(new SimpleGrantedAuthority(userRoles));
	}
}
