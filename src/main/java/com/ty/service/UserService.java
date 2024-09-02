package com.ty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import com.ty.dao.UserDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Address;
import com.ty.entity.Users;
import com.ty.exception.AddressNotAvailableException;
import com.ty.exception.IdNotFoundException;
import com.ty.exception.UserNotFoundException;
import com.ty.repository.AddressRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	private AddressRepository addressRepository;

	public ResponseEntity<ResponseStructure<Users>> saveUser(@Valid Users user, @CookieValue String role) {
		ResponseStructure<Users> responseStructure = new ResponseStructure<>();
		if (role.equals("ADMIN")) {
			Address address = addressRepository.findById(user.getAddress().getId()).get();
			if (address == null) {
				throw new AddressNotAvailableException("Address not found");
			}
			userDao.saveUser(user);
			// ResponseStructure<Users> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(user);
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
		} else {
			responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
			responseStructure.setData(null);
			responseStructure.setMessage("u r not a admin ....");
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.FORBIDDEN);

		}
	}

	public ResponseEntity<ResponseStructure<List<Users>>> findAll() {

		List<Users> users = userDao.findAllUsers();
		if (users == null) {
			throw new UserNotFoundException("no users available");
		}
		ResponseStructure<List<Users>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setData(users);
		responseStructure.setMessage("success");
		return new ResponseEntity<ResponseStructure<List<Users>>>(responseStructure, HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<Users>> delete(int id) {
		Optional<Users> find = userDao.findById(id);
		ResponseStructure<Users> responseStructure = new ResponseStructure<>();
		if (find.isEmpty()) {
			throw new IdNotFoundException("id not available");
		} else {
			userDao.deleteById(id);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setData(null);
			responseStructure.setMessage("deleted");
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.OK);
		}

	}

}
