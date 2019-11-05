package com.hcl.login;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

/**
 * interface used for User crud operations (User Repository)
 * @author AjeetY
 *
 */

public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * method to find all the users in database.
	 */
	List<User> findAll();

	/**
	 * method to find the user on the basis of userName/email id.
	 * 
	 * @param userName this is parameter by search query should perform to find
	 *                 user. it must be email id.
	 * @return User object if provided userName exist.
	 */
	User findByuserName(String userName);
}
