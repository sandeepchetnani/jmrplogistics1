package com.ty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Cargo {

	@Id
	private int id;
	@Size(min=1,max=20,message = "Cargo name is mandatory")
	private String cargoName;

	@Size(max = 500, message = "Cargo description must be less than or equal to 500 characters")
	private String cargoDescription;

	@Min(value = 1, message = "Cargo weight must be a positive integer")
	private int cargoWeight;

	@Min(value = 1, message = "Cargo count must be a positive integer")
	private int cargoCount;

}
