package com.hosting.rest.api.mapper;

import static com.hosting.rest.api.controllers.User.UserRole.ROLE_ADMIN_USER;
import static com.hosting.rest.api.controllers.User.UserRole.ROLE_BASE_USER;
import static com.hosting.rest.api.controllers.User.UserRole.ROLE_HOST_USER;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${booking.app.admin.id}")
	private static String adminId;
	
	@Value("${booking.app.admin.name}")
	private static String adminName;
	
	@Value("${booking.app.admin.email}")
	private static String adminEmail;

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
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		// Usuario admin
		if (user.getName().equals(adminName) && user.getEmail().equals(adminEmail)
				&& user.getId() == Integer.parseInt(adminId)) {
			authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN_USER.toString()));
		} else {
			
			if(user instanceof UserModel) {
				authorities.add(new SimpleGrantedAuthority(ROLE_BASE_USER.toString()));
			}
			
			if(user instanceof UserHostModel) {
				authorities.add(new SimpleGrantedAuthority(ROLE_HOST_USER.toString()));
			}
		}

		return authorities;
	}
}
