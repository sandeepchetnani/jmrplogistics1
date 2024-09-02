package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.entity.Loading;

@Repository
public interface LoadingRepository extends JpaRepository<Loading, Integer> {
	

}
