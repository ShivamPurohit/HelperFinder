package com.major.one.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.one.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
	

}
