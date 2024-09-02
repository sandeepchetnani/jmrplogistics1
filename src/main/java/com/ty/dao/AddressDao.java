package com.ty.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Address;
import com.ty.repository.AddressRepository;

@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address)
	{
		return addressRepository.save(address);
	}
	

}
