package com.hosting.rest.api.services.UserDetails;

import static com.hosting.rest.api.controllers.User.UserRole.ROLE_ADMIN_USER;
import static com.hosting.rest.api.controllers.User.UserRole.ROLE_BASE_USER;
import static com.hosting.rest.api.controllers.User.UserRole.ROLE_HOST_USER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hosting.rest.api.models.User.UserHostModel;
import com.hosting.rest.api.models.User.UserModel;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -7556049289952103543L;

	private Integer id;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;

	}

	public static UserDetailsImpl build(final UserModel user) {

		List<GrantedAuthority> authorities = getAuthoritiesList(user);

		return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPass(), authorities);
	}

	public static List<GrantedAuthority> getAuthoritiesList(final UserModel user) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		// Usuario admin
		if (user.getName().equals("administrator") && user.getEmail().equals("admin@leoncamp.es")
				&& user.getId() == 7) {
			authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN_USER.toString()));
		} else {

			if (user instanceof UserModel) {
				authorities.add(new SimpleGrantedAuthority(ROLE_BASE_USER.toString()));
			}

			if (user instanceof UserHostModel) {
				authorities.add(new SimpleGrantedAuthority(ROLE_HOST_USER.toString()));
			}
		}

		return authorities;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
