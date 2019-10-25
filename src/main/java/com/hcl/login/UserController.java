package com.hcl.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	Response response;

	@PostMapping("/signup")
	private Response signUpUser(@RequestBody User user) {
		Response responseFromSignup = userService.signUp(user);
		return responseFromSignup;
	}

	@PostMapping("/login")
	private Response authentication (@RequestParam String username, @RequestParam String password) {
		 boolean login = userService.auth(username,password);
		 if(login) {
			 response.setIsAuthenticated("true");
		 }else {
			 response.setIsAuthenticated("false");
		 }
		return response;
       }
	
	@PostMapping("/changePassword")
	private Response changePassword (@RequestParam String oldPassword,  @RequestParam String newPassword, @RequestParam String userName ) {
		boolean isUpdated = userService.changePassword(oldPassword,newPassword,userName);
		if(isUpdated) {
			response.setIsUpdated("true");
		}else {
			response.setIsUpdated("false");
		}	
		return response;
       }

	
	//method for test
	@GetMapping("/getAllUsers")
	private List<User> getAllUsers() {
		return userService.getAllPersons();
	}

	
	
}
