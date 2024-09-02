package com.ty.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Cargo;
import com.ty.repository.CargoRepository;

@Repository
public class CargoDao {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	public Cargo saveCargo(Cargo cargo)
	{
		return cargoRepository.save(cargo);
	}
	
	

}