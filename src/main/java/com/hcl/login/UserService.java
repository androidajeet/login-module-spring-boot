package com.hcl.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	Response response;

	public List<User> getAllPersons() {
		List<User> users = userRepository.findAll();
		return users;
	}



	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}

	public boolean auth(String username ,String password ) {
		// existedUser.getPassword().equals(user.getPassword())
		boolean login = false;
		//User existedUser = getUserByUsername(username);
		User existedUser = userRepository.findByuserName(username);
		if (existedUser != null) {
			if (PasswordUtils.verifyUserPassword(password, existedUser.getPassword(), PasswordUtils.getSalt(30))    ) {
				login = true;
			}
		} else {
			login = false;
		}
		return login;
	}

	public boolean changePassword(String oldPassword, String newPassword, String userName) {
		// existedUser.getPassword().equals(oldPassword)
		boolean isUpdated = false;
		//User existedUser = getUserByUsername(userName);
		User existedUser = userRepository.findByuserName(userName);
		if (existedUser != null) {
			if (PasswordUtils.verifyUserPassword(oldPassword, existedUser.getPassword(), PasswordUtils.getSalt(30))) {
				existedUser.setPassword(PasswordUtils.generateSecurePassword(newPassword, PasswordUtils.getSalt(30))    );
				saveOrUpdate(existedUser);
				isUpdated = true;
			}
		} else {
			isUpdated = false;
		}
		return isUpdated;
	}

	public Response signUp(User user) {
		if (user.getUserName().length() <= 0 || user.getName().length() <= 0 || user.getDob().length() <= 0
				|| user.getSex().length() <= 0 || user.getPassword().length() <= 0) {
			response.setStatus("failed");
			response.setLoginUrl("");
		} else {
			User isUserExist = null;
			try {
				//isUserExist = getUserByUsername(user.getUserName());
				isUserExist = userRepository.findByuserName(user.getUserName());
				// System.out.println("isUserExist:" + isUserExist);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isUserExist == null) {
				// Generate Salt.
				String salt = PasswordUtils.getSalt(30);
				// Protect user's password. The generated value can be stored in DB.
				String securePassword = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
				user.setPassword(securePassword);

				saveOrUpdate(user);
				response.setStatus("success");
				response.setLoginUrl("http://shoppingcart.com/login");
			} else {
				response.setStatus("failed");
				response.setLoginUrl("");
			}
		}
		return response;
	}

}
