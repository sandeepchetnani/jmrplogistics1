package com.ty.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDto {
	
	
	private int id;
	
	private String dateOfOrder;

	@NotBlank(message = "Order status is mandatory")
	private String orderStatus;

	private int freightCost;

	@Size(max = 500, message = "Additional info must be less than or equal to 500 characters")
	private String additionalInfo;
	
	private int cargiId;
	private int carrierId;
	private int loadingId;
	private int unloadingId;
	private int loaduserId;
	private int unloadId;
	
	
	
	
}
