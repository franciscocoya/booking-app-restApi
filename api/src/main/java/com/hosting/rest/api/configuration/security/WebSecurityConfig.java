package com.hosting.rest.api.configuration.security;

import static com.hosting.rest.api.Utils.Constants.API_LOGOUT_URL;
import static com.hosting.rest.api.Utils.Constants.GRANTED_AUTH_PATH;
import static com.hosting.rest.api.Utils.Constants.CORS_ALLOWED_ORIGINS_PATH;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.hosting.rest.api.services.User.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {	
		httpSecurity.requiresChannel()
	      .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
	      .requiresSecure().and().cors().and().csrf().disable()
	      .exceptionHandling()
	      .authenticationEntryPoint(authEntryPoint).and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and().authorizeRequests().antMatchers(GRANTED_AUTH_PATH).permitAll()
	      .antMatchers(HttpMethod.GET, "/accomodations/**", "/users/**").permitAll()
	      .anyRequest().authenticated();

		// Configuración de CORS
		httpSecurity.addFilterBefore(new CorsFilter(corsConfigurationSource(CORS_ALLOWED_ORIGINS_PATH)),
				AbstractPreAuthenticatedProcessingFilter.class);

		// Filtro autenticación
		httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		// Configuración logout
		httpSecurity.logout(
				logout -> logout.logoutUrl(API_LOGOUT_URL).deleteCookies("JSESSIONID").invalidateHttpSession(true));

	}

	/**
	 * Configuración de CORS para la api.
	 * 
	 * @param corsOrigin
	 * @return
	 */
	private CorsConfigurationSource corsConfigurationSource(final String corsOrigin) {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList(corsOrigin));

		configuration.applyPermitDefaultValues();

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
