package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.entity.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {

}
