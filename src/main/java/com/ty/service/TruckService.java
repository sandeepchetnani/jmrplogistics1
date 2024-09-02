package com.ty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.TruckDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Truck;

import jakarta.validation.Valid;

@Service
public class TruckService {

	@Autowired
	TruckDao truckDao;

	public ResponseEntity<ResponseStructure<Truck>> saveTruck(@Valid Truck truck, String role) {
		ResponseStructure<Truck> responseStructure = new ResponseStructure<>();
		try {
	        if (role.equals("ADMIN")) {
	            truckDao.saveTruck(truck);
	            responseStructure.setStatusCode(HttpStatus.CREATED.value());
	            responseStructure.setData(truck);
	            responseStructure.setMessage("Truck saved successfully.");
	            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	        } else {
	            responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
	            responseStructure.setData(null);
	            responseStructure.setMessage("You are not authorized to perform this action.");
	            return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
	        }
	    } catch (Exception ex) {
	        responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        responseStructure.setData(null);
	        responseStructure.setMessage("An error occurred while saving the truck: " + ex.getMessage());
	        return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
