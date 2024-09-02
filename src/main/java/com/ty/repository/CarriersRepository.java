package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.entity.Carrier;

@Repository
public interface CarriersRepository extends JpaRepository<Carrier, Integer> {

	
}
