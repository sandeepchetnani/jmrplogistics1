 package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.entity.Unloading;

@Repository
public interface UnloadingRepository extends JpaRepository<Unloading, Integer> {
	
	

}
