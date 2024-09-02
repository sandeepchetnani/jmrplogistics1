package com.ty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.DriverDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Carrier;
import com.ty.entity.Driver;
import com.ty.exception.CarrierNotPresent;
import com.ty.repository.CarriersRepository;

import jakarta.validation.Valid;

@Service
public class DriverService {

	@Autowired
	DriverDao driverDao;
	@Autowired
	CarriersRepository carriersRepository;
	
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@Valid Driver driver,String role) 
	{
		ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
		if(role.equals("ADMIN"))
		{
		Carrier carrier=carriersRepository.findById(driver.getCarrier().getId()).get();
		
		if(carrier ==null) {
		  throw new CarrierNotPresent("Carrier not present");
		}
		
		driver.setCarrier(carrier);
		driverDao.saveDriver(driver);
	
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setData(driver);
		responseStructure.setMessage("success");
		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.CREATED);
		}
		else
		{
			responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
			responseStructure.setData(null);
			responseStructure.setMessage("u r not a admin ....");
			return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.FORBIDDEN);

		}
	}
	
	public ResponseEntity<ResponseStructure<Driver>> updateDriver(@Valid Driver driver,String role) 
	{
		ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
		if(role.equals("Driver"))
		{
		Carrier carrier=carriersRepository.findById(driver.getCarrier().getId()).get();
		
		if(carrier ==null) {
		  throw new CarrierNotPresent("Carrier not present");
		}
		
		driver.setCarrier(carrier);
		driverDao.saveDriver(driver);
	
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setData(driver);
		responseStructure.setMessage("success");
		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.CREATED);
		}
		else
		{
			responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
			responseStructure.setData(null);
			responseStructure.setMessage("u r not a Driver ....");
			return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.FORBIDDEN);

		}
	}
	
	
	
}
