package com.hcl.login;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LoginApplication.class)
class LoginApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	User user;
	@Autowired
	Response response;
	
	@Test
	void saltGenration() {
		String salt = PasswordUtils.getSalt(30);
		System.out.println(salt);
		// check if the value is correct
		assertEquals(salt, "222222222222222222222222222222");
	}

	
	@Test
	void passwordEncryption() {
		String encryptedPassword = PasswordUtils.generateSecurePassword("12345", "222222222222222222222222222222");
		System.out.println(encryptedPassword);
		assertEquals("mkDxC+GWBulIe9XXl7Bg/7THNwWNEGwzN1EOm1GV3a0=", encryptedPassword);
	}
	
	
	@Test
	void verifyPassword() {
		boolean isVerified = PasswordUtils.
				verifyUserPassword("12345", "mkDxC+GWBulIe9XXl7Bg/7THNwWNEGwzN1EOm1GV3a0=", "222222222222222222222222222222");
		
		assertEquals(true, isVerified);
	}
	
	
//	@Before
//	public void before() {
//		System.out.println("Before");
//		user.setSex("M");
//		user.setDob("1/2/1990");
//		user.setName("Ajeet yadav");
//		user.setPassword("12345");
//		user.setUserName("ajeety@hcl.com");
//		
//		
//		
//		response.setLoginUrl("http://shoppingcart.com/login");
//		response.setStatus("success");
//	}
	@Test
	void signUpTest() {
//		user.setSex("M");
//		user.setDob("1/2/1990");
//		user.setName("Ajeet yadav");
//		user.setPassword("12345");
//		user.setUserName("ajeety@hcl.com");
//		
//		
//		
//		response.setLoginUrl("http://shoppingcart.com/login");
//		response.setStatus("success");
//		response.setIsAuthenticated(null);
//		response.setIsUpdated(null);
//		
//		
//		Response actualResponse = userService.signUp(user);
//		assertEquals(response, actualResponse);
		
	}
	
}
