package com.ty.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Loading {

	@Id
	private int id;

	@NotBlank(message = "Company name is mandatory")
	private String companyName;

	@NotBlank(message = "Loading date is mandatory")
	private String loadingDate;

	@NotBlank(message = "Loading time is mandatory")
	private String loadingTime;

	@OneToOne
	private Address address;

}
