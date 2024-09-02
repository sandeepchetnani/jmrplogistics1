package com.ty.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Driver;
import com.ty.repository.DriverRepository;

@Repository
public class DriverDao {
	@Autowired
	private DriverRepository driverRepository;
	
	public Driver saveDriver(Driver driver)
	{
		return driverRepository.save(driver);
	}

}
