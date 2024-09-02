package com.ty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Users {

	@Id
	private int id;

	@NotBlank(message = "User name is mandatory")
	@Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters")
	private String userName;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	private Long userPhoneNumber;

	@NotBlank(message = "User role is mandatory")
	private String userRole;
	@OneToOne
	private Address address;

	

}
