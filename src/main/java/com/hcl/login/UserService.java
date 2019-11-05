package com.hcl.login;

import java.util.List;

/**
 * User Service
 * 
 * @author AjeetY
 *
 */

public interface UserService {

	/**
	 * sign up the user.
	 * 
	 * @param user user object.
	 * @return saved user.
	 */
	public User signUp(User user);

	/**
	 * for authentication of user.
	 * 
	 * @param username username of user.
	 * @param password password of user.
	 * @return if user authenticated or not.
	 */
	public boolean auth(String username, String password);

	/**
	 * to change the password.
	 * 
	 * @param oldPassword existing password of user.
	 * @param newPassword new password.
	 * @param username    username of user.
	 * @return if password changed successfully or not.
	 */
	public boolean changePassword(String oldPassword, String newPassword, String username);

	/**
	 * save the user to database.
	 * 
	 * @param user user object.
	 * @return saved user.
	 */
	public User saveOrUpdate(User user);

	/**
	 * list of all users.
	 * 
	 * @return List of users.
	 */
	public List<User> getAllPersons();
}
