package com.hosting.rest.api.services.Auth;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Auth.ResetPasswordPayload;
import com.hosting.rest.api.models.Auth.SignUpRequest;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.repositories.User.IUserRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Servicio que gestiona la autenticación de usuarios.
 *
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUserRepository userRepo;

	/**
	 * Crear un nuevo usuario con nombre, apellidos, email y contraseña.
	 * 
	 * @param signUpPayload
	 * 
	 * @return
	 * 
	 */
	@Override
	public ResponseEntity<?> addNewUser(final SignUpRequest signUpPayload) {
		// Validar el usuario pasado como parámetro
		validateParam(isNotNull(signUpPayload), "Alguna propiedad del usuario a crear falta o no es válida.");

		// Comprobar que coinciden las contraseñas
		if (!signUpPayload.getPassword().equals(signUpPayload.getRepeatedPassword())) {
			return ResponseEntity.badRequest().body("Las contraseñas no coinciden");
		}

		// Comprobar si existe el usuario
		if (userRepo.existsByEmail(signUpPayload.getEmail())) {
			return ResponseEntity.badRequest().body("Ya existe un usuario con el email " + signUpPayload.getEmail());
		}

		// Datos del nuevo usuario
		UserModel newUser = new UserModel(signUpPayload.getName(), signUpPayload.getSurname(), signUpPayload.getEmail(),
				passwordEncoder.encode(signUpPayload.getPassword()));

		// Crear el usuario
		userRepo.save(newUser);

		return ResponseEntity.ok(newUser);
	}

	@Override
	public ResponseEntity<?> resetPassword(final Integer userId, final ResetPasswordPayload passwordPayload) {
		// Validar nueva contraseña
		if (!isStringNotBlank(passwordPayload.getOldPassword())) {
			return ResponseEntity.badRequest().body("La contraseña no puede estar vacía");
		}

		// Comprobar si existe el usuario
		if (!userRepo.existsById(userId)) {
			return ResponseEntity.badRequest().body("El usuario no existe.");
		}
		
		UserModel userToUpdate = userRepo.findById(userId).get();
		
		String newPasswordEncoded = passwordEncoder.encode(passwordPayload.getNewPassword());
		
		// Comprobar si la contraseña actual es correcta
		if (!passwordEncoder.matches(passwordPayload.getOldPassword(), userToUpdate.getPass())) {
			return ResponseEntity.badRequest().body("La contraseña actual no es válida." + "\n" + passwordPayload.getOldPassword() + "\n" + userToUpdate.getPass());
		}
		
		// Comprobar que las contraseñas coinciden
		if (!passwordEncoder.matches(passwordPayload.getNewPasswordRepeated(), newPasswordEncoded)) {
			return ResponseEntity.badRequest().body("La contraseñas no coinciden.");
		}	

		// Actualizar contraseña
		userToUpdate.setPass(newPasswordEncoded);

		userRepo.save(userToUpdate);

		return ResponseEntity.ok("Contraseña actualizada correctamente");
	}
}
