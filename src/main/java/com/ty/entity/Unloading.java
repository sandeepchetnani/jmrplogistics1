package com.ty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Unloading {
	@Id
	private int id;
	@NotBlank(message = "Company name is mandatory")
	private String companyName;

	@NotBlank(message = "Unloading date is mandatory")
	private String unLoadingDate;

	@NotBlank(message = "Unloading time is mandatory")
	private String unLoadingTime;
	@OneToOne
	private Address address;


}
