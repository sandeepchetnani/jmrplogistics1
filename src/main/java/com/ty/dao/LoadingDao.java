package com.ty.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.entity.Loading;
import com.ty.repository.LoadingRepository;

@Repository
public class LoadingDao {
	@Autowired
	private LoadingRepository loadingRepository;
	
	public Loading saveLoading(Loading loading)
	{
		return loadingRepository.save(loading);
	}
}
