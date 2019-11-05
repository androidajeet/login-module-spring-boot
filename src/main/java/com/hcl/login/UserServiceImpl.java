package com.hcl.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for user Service it use for user related business logic (User Service)
 * 
 * @author AjeetY
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
 
	/**implementation of method getAllPersons(),
	 * query user repository for getting list of all users from db.
	 * 
	 * @return list of all users in db
	 */
	@Override
	public List<User> getAllPersons() {
		List<User> users = userRepository.findAll();
		return users;
	}

	/**implementation of method saveOrUpdate(),
	 * create a new user or to commit the changes in user object.
	 * 
	 * @param user it take user object to save/change the user
	 */
	@Override
	public User saveOrUpdate(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
 
	/**implementation of method saveOrUpdate(),
	 * method for authenticate user in database.
	 * 
	 * @param username email id of user.
	 * @param password password of user.
	 * @return if user having authorized access or not, based on provided username
	 *         and password.
	 */
	@Override
	public boolean auth(String username, String password) {
		boolean login = false;
		User existedUser = userRepository.findByuserName(username);
		if (existedUser != null) {
			if (PasswordUtils.verifyUserPassword(password, existedUser.getPassword(), PasswordUtils.getSalt(30))) {
				login = true;
			}
		} else {
			login = false;
		}
		return login;
	}

	/**implementation of method changePassword(),
	 * method for changing password for a user.
	 * 
	 * @param oldPassword existing password of user.
	 * @param newPassword new password that user want in place of existing password.
	 * @param userName    username/email id of user.
	 * @return if password is successfully changed or not.
	 */
	@Override
	public boolean changePassword(String oldPassword, String newPassword, String userName) {
		boolean isUpdated = false;
		User existedUser = userRepository.findByuserName(userName);
		if (existedUser != null) {
			if (PasswordUtils.verifyUserPassword(oldPassword, existedUser.getPassword(), PasswordUtils.getSalt(30))) {
				existedUser.setPassword(PasswordUtils.generateSecurePassword(newPassword, PasswordUtils.getSalt(30)));
				saveOrUpdate(existedUser);
				isUpdated = true;
			}
		} else {
			isUpdated = false;
		}
		return isUpdated;
	}

	/**implementation of method signUp(),
	 * method for creating/registering new user to the db.
	 * 
	 * @param user User object.
	 * @return Response object with the status of success or failure and login url
	 *         detail.
	 */
	@Override
	public User signUp(User user) {
		User isUserExist = null;
			isUserExist = userRepository.findByuserName(user.getUserName());
			if (isUserExist == null) {
				// Generate Salt.
				String salt = PasswordUtils.getSalt(30);
				// Protect user's password. The generated value can be stored in DB.
				String securePassword = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
				user.setPassword(securePassword);
				User savedUser = saveOrUpdate(user);
				return savedUser;
			} else {
				return null;
			}
		}
		
	

}
