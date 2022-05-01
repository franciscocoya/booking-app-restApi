package com.hosting.rest.api.services.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Servicio para la gestion de detalles de usuario de login.
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		UserModel user = userRepo.findByEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException("No existe ningún usuario con email : " + userEmail));

		return UserDetailsImpl.build(user);
	}
}
