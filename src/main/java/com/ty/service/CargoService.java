package com.ty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.CargoDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Cargo;

import jakarta.validation.Valid;

@Service
public class CargoService {

	@Autowired
	CargoDao cargoDao;
	
	public ResponseEntity<ResponseStructure<Cargo>> saveCargo(@Valid Cargo cargo,String role)
	{
		ResponseStructure<Cargo> responseStructure = new ResponseStructure<>();
		 try {
		        if (role.equals("ADMIN")) {
		            cargoDao.saveCargo(cargo);
		            responseStructure.setStatusCode(HttpStatus.CREATED.value());
		            responseStructure.setData(cargo);
		            responseStructure.setMessage("Cargo saved successfully.");
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
		        responseStructure.setMessage("An error occurred while saving the cargo: " + e.getMessage());
		        return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
}
