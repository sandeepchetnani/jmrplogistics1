package com.ty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.UnloadingDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Address;
import com.ty.entity.Unloading;
import com.ty.exception.AddressNotAvailableException;
import com.ty.repository.AddressRepository;

import jakarta.validation.Valid;

@Service
public class UnloadingService {

	@Autowired
	UnloadingDao unloadingDao;
	@Autowired
	AddressRepository addressRepository;

	public ResponseEntity<ResponseStructure<Unloading>> saveUnLoading(@Valid Unloading unloading, String role) {
		ResponseStructure<Unloading> responseStructure = new ResponseStructure<>();
		 try {
		        if (role.equals("ADMIN")) {
		            Optional<Address> optionalAddress = addressRepository.findById(unloading.getAddress().getId());

		            if (optionalAddress.isEmpty()) {
		                throw new AddressNotAvailableException("Address not found");
		            }
		            unloading.setAddress(optionalAddress.get());
		            unloadingDao.saveUnloading(unloading);

		            responseStructure.setStatusCode(HttpStatus.CREATED.value());
		            responseStructure.setData(unloading);
		            responseStructure.setMessage("Unloading saved successfully.");
		            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		        } else {
		            responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
		            responseStructure.setData(null);
		            responseStructure.setMessage("You are not authorized to perform this action.");
		            return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
		        }
		    } catch (AddressNotAvailableException ex) {
		        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		        responseStructure.setData(null);
		        responseStructure.setMessage(ex.getMessage());
		        return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		    } catch (Exception ex) {
		        responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		        responseStructure.setData(null);
		        responseStructure.setMessage("An error occurred while saving the unloading: " + ex.getMessage());
		        return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

}
