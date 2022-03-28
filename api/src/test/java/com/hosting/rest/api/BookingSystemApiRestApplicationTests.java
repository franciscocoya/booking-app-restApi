package com.hosting.rest.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hosting.rest.api.controllers.User.UserController;
import com.hosting.rest.api.models.User.UserModel;

@SpringBootTest
class BookingSystemApiRestApplicationTests {
	
	private static final int DEFAULT_SERVER_PORT = 8085;
	private static final String DEFAULT_URI_HEAD = "http://localhost:" + DEFAULT_SERVER_PORT + "/api/";
	
	@Autowired
	private static UserController userController;

	@Test
	void testAddNewUser() {
        //UserModel userToAdd = new UserModel(5, "María", "Becker", "maria.becker@gmail.com", "999876767", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", null, null);
         
        //UserModel newAddeduser = userController.addNewUser(userToAdd);
         
        //Verify request succeed
        //assertTrue(newAddeduser.getCreatedAt() != null);
	}

}
