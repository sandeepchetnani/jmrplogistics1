package com.ty.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Users;
import com.ty.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private  UserRepository userRepository;
	
	public Users saveUser(Users user)
	{
		return userRepository.save(user);
	}
	
	public List<Users> findAllUsers()
	{
		return userRepository.findAll();
	}
	
	public void deleteById(int id)
	{
		userRepository.deleteById(id);
	}
	public Optional<Users> findById(int id)
	{
		return userRepository.findById(id);
	}

}
