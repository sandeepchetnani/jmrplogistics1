package com.ty.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Carrier;
import com.ty.repository.CarriersRepository;

@Repository
public class CarrierDao {

	@Autowired
	private CarriersRepository carriersRepository;
	
	public Carrier saveCarrier(Carrier carrier) 
	{
		
		return carriersRepository.save(carrier);
	}
}
