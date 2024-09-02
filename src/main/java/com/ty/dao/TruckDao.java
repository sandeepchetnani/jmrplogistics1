package com.ty.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Truck;
import com.ty.repository.TruckRepository;

@Repository
public class TruckDao {
	
	@Autowired
	TruckRepository truckRepository;

	public Truck saveTruck(Truck truck)
	{
		return truckRepository.save(truck);
	}

}
