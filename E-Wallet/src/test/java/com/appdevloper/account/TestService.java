package com.appdevloper.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.appdevloper.account.model.UsersModel;
import com.appdevloper.account.service.UserService;
@SpringBootTest
class TestService {

	@Autowired
	private UserService userService;
	
	@Test
	@Order(1)
	public void testUserAdding()
	{
		UsersModel usersModel = new UsersModel();
		usersModel.setUsername("test9");
		usersModel.setEmail("test9@test.com");
		usersModel.setPassword("akhil123456");
		
		userService.addUser(usersModel);
		
		assertNotNull(userService.loadUserByUsername("test9"));
	}
	
	@Test
	@Order(2)
	public void updateUserData(){
		UsersModel usersModel = userService.loadUserByUsername("test9");
		UsersModel user = new  UsersModel();
		user.setId(usersModel.getId());
		user.setUsername(usersModel.getUsername());
		user.setEmail(usersModel.getPassword());
		user.setPassword("1234567890");
		
		assertNotEquals(user.getPassword(), usersModel.getPassword());
	}
	
	@Test
	@Order(3)
	public void testUserGetByUsername() {
		
		assertNotNull(userService.loadUserByUsername("test9"));
	}
	
	@Test
	@Order(4)
	public void testUserGetByEmail() {
		
		assertNotNull(userService.loadUserByEmail("test9@test.com"));
	}
	

	
	@Test
	@Order(5)
	public void testDeleteOperation() {
		
		
		 userService.deleteUser(userService.loadUserByUsername("test9"));
		 assertNull(userService.loadUserByEmail("test9@test.com"));
		
	}
	

}
