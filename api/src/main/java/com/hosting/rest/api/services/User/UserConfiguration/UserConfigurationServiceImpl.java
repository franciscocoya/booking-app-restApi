package com.hosting.rest.api.services.User.UserConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;
import com.hosting.rest.api.repositories.User.UserConfiguration.IUserConfigurationRepository;

@Service
public class UserConfigurationServiceImpl implements IUserConfigurationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserConfigurationRepository userConfigurationRepo;

	@Override
	public UserConfigurationModel addNewUserConfiguration(final UserConfigurationModel newUserConfigurationModel) {
		// TODO: Comprobar parametro
		return userConfigurationRepo.save(newUserConfigurationModel);
	}

	@Override
	public UserConfigurationModel updateUserConfiguration(final Integer userId,
			UserConfigurationModel userConfigurationModelToUpdate) {
		// TODO: Comprobar parametro

		return null;
	}

	@Override
	public UserConfigurationModel findById(final Integer userConfigurationId) {
		// TODO: Comprobar parametro
		return userConfigurationRepo.findById(userConfigurationId).get();
	}

	@Override
	public UserConfigurationModel findByUserId(final Integer userId) {
		// TODO: Comprobar parametro

		String findByUserIdQuery = "SELECT ucm " + "FROM UserModel um INNER JOIN um.idUserConfiguration ucm "
				+ "WHERE um.id = :userId";

		TypedQuery<UserConfigurationModel> userConfig = em.createQuery(findByUserIdQuery, UserConfigurationModel.class);

		userConfig.setParameter("userId", userId);

		return userConfig.getSingleResult();
	}

	@Override
	public void deleteUserConfigurationByUserId(final Integer userId) {
		// TODO: Comprobar parametro
		String findByUserIdQuery = "DELETE FROM UserConfigurationModel ucm, UserModel um "
				+ "WHERE um.id = :userId AND um.idUserConfiguration.id = ucm.id";

		TypedQuery<UserConfigurationModel> userConfig = em.createQuery(findByUserIdQuery, UserConfigurationModel.class);

		userConfig.setParameter("userId", userId);
	}

	@Override
	public void deleteUserConfiguration(Integer userConfigurationId) {
		// TODO: Comprobar parámetro
		userConfigurationRepo.deleteById(userConfigurationId);
		
	}

}
