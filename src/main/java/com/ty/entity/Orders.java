package com.ty.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Orders {

	@Id
	private int id;

	private String dateOfOrder;

	@NotBlank(message = "Order status is mandatory")
	private String orderStatus;

	private int freightCost;

	@Size(max = 500, message = "Additional info must be less than or equal to 500 characters")
	private String additionalInfo;

	@ManyToOne
	Carrier carrier;

	@OneToOne
	Cargo cargo;

	@OneToOne(cascade = CascadeType.ALL)
	Loading loading;

	
	@OneToOne(cascade = CascadeType.ALL)
	Unloading unloading;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Users> loadingUser;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Users> unloadingUser;

}
