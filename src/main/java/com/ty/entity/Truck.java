package com.ty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Truck {

	@Id
	private int id;
	@NotBlank(message = "Name is mandatory")
	@Size(max = 100, message = "Name must be less than or equal to 100 characters")
	private String name;

	private long registeredNumber;

	private int capacity;

	@NotBlank(message = "Status is mandatory")
	private String status;

	private int carrierId;

}
