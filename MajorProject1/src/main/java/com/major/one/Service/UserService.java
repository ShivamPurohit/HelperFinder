package com.major.one.Service;


import java.util.List;
import com.major.one.model.User;


public interface UserService {
	User getValidUser(String email,String pass);
	
//	String addUser(String email,String pass,String username,String city,String state);
	User addUser(User user);

	User fetchByUserEmailId(String email);
	
	List<User> getAllCustomer();
	boolean sendEmail(String res,String email);
}
