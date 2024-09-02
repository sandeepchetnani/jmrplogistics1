package com.ty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Driver {

	@Id
	private int id;
	@NotBlank(message = "Driver name is mandatory")
	@Size(max = 100, message = "Driver name must be less than or equal to 100 characters")
	private String driverName;

	private long phoneNumber;

	private int registeredNumber;

	
	@ManyToOne
	@JoinColumn(name = "carrier_id")
	private Carrier carrier;

}
