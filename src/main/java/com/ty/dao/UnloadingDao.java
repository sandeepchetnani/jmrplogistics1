package com.ty.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Unloading;
import com.ty.repository.UnloadingRepository;

@Repository
public class UnloadingDao {
	@Autowired
	private UnloadingRepository unloadingRepository;
	
	public Unloading saveUnloading(Unloading unloading)
	{
		return unloadingRepository.save(unloading);
	}
}
