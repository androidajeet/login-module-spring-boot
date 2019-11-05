package com.hcl.login;

import java.util.List;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.login.exception.ChangePasswordException;
import com.hcl.login.exception.LogInUserException;
import com.hcl.login.exception.SignUpUserException;
import com.hcl.login.responseEntities.ChangePasswordSuccessResponse;
import com.hcl.login.responseEntities.LoginSuccessResponse;
import com.hcl.login.responseEntities.SignUpSuccessResponse;

/**
 * Controller/Resource class for creating the endpoints which are exposed to
 * clients for making request to the user repository.
 * 
 * @author AjeetY
 *
 */

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	SignUpSuccessResponse signUpSuccessResponse;

	@Autowired
	ChangePasswordSuccessResponse changePasswordResponse;

	@Autowired
	LoginSuccessResponse logInResponse;

	/**
	 * Endpoint for sign up.
	 * 
	 * @param user User object
	 * @return Response object containing response of this request.
	 */
	@PostMapping("/signup")
	private SignUpSuccessResponse signUpUser(@Valid @RequestBody(required = true) User user) {
		if (user.getUserName().equals("") || user.getName().equals("") || user.getDob().equals("")
				|| user.getSex().equals("") || user.getPassword().equals("")) {

			throw new SignUpUserException("failed");

		} else {
			User responseFromSignup = userService.signUp(user);
			if (responseFromSignup != null) {
				signUpSuccessResponse.setStatus("success");
				signUpSuccessResponse.setLoginUrl("http://shoppingcart.com/login");

			} else
				throw new SignUpUserException("failed");

		}

		return signUpSuccessResponse;
	}

	/**
	 * Endpoint for log in.
	 * 
	 * @param username email id of user.
	 * @param password password of user.
	 * @return Response object containing response of this request.
	 * @throws LoginException
	 */
	@PostMapping("/login")
	private LoginSuccessResponse authentication(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password) {
		boolean login = userService.auth(username, password);

		System.out.println(login);
		if (login) {
			logInResponse.setIsAuthenticated("true");
		} else {
			throw new LogInUserException("false");
		}
		return logInResponse;
	}

	/**
	 * Endpoint for changing the password.
	 * 
	 * @param oldPassword old password of user.
	 * @param newPassword desired password of user
	 * @param userName    email id of user.
	 * @return Response object containing response of this request.
	 */
	@PostMapping("/changePassword")
	private ChangePasswordSuccessResponse changePassword(@RequestParam(required = true) String oldPassword,
			@RequestParam(required = true) String newPassword, @RequestParam(required = true) String userName) {

		boolean isUpdated = userService.changePassword(oldPassword, newPassword, userName);
		if (isUpdated) {
			changePasswordResponse.setIsUpdated("true");
		} else {
			throw new ChangePasswordException("false");

		}
		return changePasswordResponse;
	}

	/**
	 * Endpoint to get list of all existing users in system.
	 * 
	 * @return list of all users.
	 * @throws Exception
	 */
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() throws Exception {
		List<User> usersList = userService.getAllPersons();
		if (usersList != null) {
			return usersList;
		} else {
			throw new Exception("Exception occured");
		}

	}

}
