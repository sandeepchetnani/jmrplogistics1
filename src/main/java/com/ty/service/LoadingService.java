package com.ty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.LoadingDao;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Address;
import com.ty.entity.Loading;
import com.ty.exception.AddressNotAvailableException;
import com.ty.repository.AddressRepository;
import jakarta.validation.Valid;

@Service
public class LoadingService {
	@Autowired
	LoadingDao loadingDao;
	@Autowired
	private AddressRepository addressRepository;
	
	public ResponseEntity<ResponseStructure<Loading>> saveLoading(@Valid Loading loading,String role)
	{
		ResponseStructure<Loading> responseStructure = new ResponseStructure<>();
		try {
	        if (role.equals("ADMIN")) {
	            Optional<Address> optionalAddress = addressRepository.findById(loading.getAddress().getId());

	            if (optionalAddress.isEmpty()) {
	                throw new AddressNotAvailableException("Address not found");
	            }

	            loading.setAddress(optionalAddress.get());
	            loadingDao.saveLoading(loading);

	            responseStructure.setStatusCode(HttpStatus.CREATED.value());
	            responseStructure.setData(loading);
	            responseStructure.setMessage("Loading saved successfully.");
	            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	        } else {
	            responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
	            responseStructure.setData(null);
	            responseStructure.setMessage("You are not authorized to perform this action.");
	            return new ResponseEntity<>(responseStructure, HttpStatus.FORBIDDEN);
	        }
	    } catch (AddressNotAvailableException ex) {
	        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        responseStructure.setData(null);
	        responseStructure.setMessage(ex.getMessage());
	        return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	    } catch (Exception ex) {
	        responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        responseStructure.setData(null);
	        responseStructure.setMessage("An error occurred while saving the loading: " + ex.getMessage());
	        return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
}