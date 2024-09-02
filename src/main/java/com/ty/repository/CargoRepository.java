package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
