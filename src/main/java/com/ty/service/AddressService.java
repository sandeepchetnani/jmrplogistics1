package com.ty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.AddressDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Address;

import jakarta.validation.Valid;

@Service
public class AddressService {
	@Autowired
	AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid Address address, String role) {
		ResponseStructure<Address> responseStructure = new ResponseStructure<>();
		try {
			if (role.equals("ADMIN")) {
				addressDao.saveAddress(address);
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setData(address);
				responseStructure.setMessage("Address saved successfully.");
				return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
			} else {
				responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
				responseStructure.setData(null);
				responseStructure.setMessage("You are not authorized to perform this action.");
				return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseStructure.setData(null);
			responseStructure.setMessage("An error occurred while saving the address: " + e.getMessage());
			return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
