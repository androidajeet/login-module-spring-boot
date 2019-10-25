package com.hcl.login;




import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends  CrudRepository<User, String>  {
	
	
	List<User> findAll();
	User findByuserName(String userName);
}
