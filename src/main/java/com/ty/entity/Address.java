package com.ty.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Address {

	//@NotBlank(message = "id is mandatory")
	@Id
	int id;

	@Size(min = 5, max = 50, message = "streetname must be between 5 and 50 characters")
	String streetName;

	int houseNumber;

	int pincode;

	@Size(min = 5, max = 50, message = "streetname must be between 5 and 50 characters")
	String district;
	@Size(min = 1, max = 20, message = "streetname must be between 5 and 50 characters")
	String state;
	@Size(min = 1, max = 20, message = "streetname must be between 5 and 50 characters")
	String country;


}
