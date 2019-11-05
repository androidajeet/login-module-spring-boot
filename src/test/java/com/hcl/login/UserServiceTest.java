package com.hcl.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	void testSignUp() {
		User userToSignUp = new User("abcy@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
				PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30)));

		when(userRepository.findByuserName(userToSignUp.getUserName())).thenReturn(null);
		when(userRepository.save(userToSignUp)).thenReturn(userToSignUp);

		User savedUser = userService.signUp(userToSignUp);

		assertEquals("abcy@hcl.com", savedUser.getUserName());
	}

	@Test
	void testSignUpWithExistingUser() {
		User userToSignUp = new User("abcy@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
				PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30)));

		when(userRepository.findByuserName(userToSignUp.getUserName())).thenReturn(userToSignUp);
		User savedUser = userService.signUp(userToSignUp);

		assertEquals(null, savedUser);
	}

	@Test
	void testAuth() {
		User user = new User("ajeety@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
				PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30)));
		when(userRepository.findByuserName(user.getUserName())).thenReturn(user);
		boolean isAuthenticated = userService.auth("ajeety@hcl.com", "12345");
		assertEquals(true, isAuthenticated);
	}

	@Test
	void testChangePassword() {
		User user = new User("ajeety@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
				PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30)));
		when(userRepository.findByuserName(user.getUserName())).thenReturn(user);

		boolean isUpdated = userService.changePassword("12345", "67890", "ajeety@hcl.com");
		assertEquals(true, isUpdated);
	}

	@Test
	void testUnAuthorisedChangePassword() {
		User user = new User("ajeety@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
				PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30)));
		when(userRepository.findByuserName(user.getUserName())).thenReturn(user);

		boolean isUpdated = userService.changePassword("4432435454", "67890", "ajeety@hcl.com");
		assertEquals(false, isUpdated);
	}

	@Test
	void testSaveOrUpdate() {
		User user = new User("ajeety@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
				PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30)));
		when(userRepository.save(user)).thenReturn(user);
		User savedUser = userService.saveOrUpdate(user);
		assertEquals("ajeety@hcl.com", savedUser.getUserName());
 
	}

	@Test
	void testGetAllPersons() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User("ajeety@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
				PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30))));

		when(userRepository.findAll()).thenReturn(userList);

		List<User> result = userService.getAllPersons();
		assertEquals(1, result.size());
	}
}


