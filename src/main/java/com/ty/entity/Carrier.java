package com.ty.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Carrier {

	@Id
	private int id;
	@NotBlank(message = "Carrier company name is mandatory")
	@Size(max = 100, message = "Carrier company name must be less than or equal to 100 characters")
	private String carrierCompanyName;

	@NotBlank(message = "Carrier email is mandatory")
    @Email(message = "Carrier email should be valid")
	private String carrierEmail;

	private long carrierContact;

	@JsonIgnore
	@OneToMany(mappedBy = "carrier")
	List<Driver> driver;

}
