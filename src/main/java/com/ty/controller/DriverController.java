package com.ty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.dto.ResponseStructure;
import com.ty.entity.Driver;
import com.ty.service.DriverService;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

	@Autowired
	DriverService driverService;

	@PutMapping("/drivers")
	public ResponseEntity<ResponseStructure<Driver>> update(@RequestBody Driver driver,@CookieValue String role) {
		return driverService.updateDriver(driver,role);
	}

}
