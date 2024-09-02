package com.ty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.CarrierDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Carrier;

import jakarta.validation.Valid;

@Service
public class CarrierService {

	@Autowired
	CarrierDao carrierDao;

	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@Valid Carrier carrier, String role) {
		ResponseStructure<Carrier> responseStructure = new ResponseStructure<>();
		try {
			if (role.equals("ADMIN")) {
				carrierDao.saveCarrier(carrier);
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setData(carrier);
				responseStructure.setMessage("Carrier saved successfully.");
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
			responseStructure.setMessage("An error occurred while saving the carrier: " + e.getMessage());
			return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
