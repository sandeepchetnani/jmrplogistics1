package com.ty.exception;

public class CarrierNotPresent extends  RuntimeException {
	 private static final long serialVersionUID = 1L;
	public CarrierNotPresent(String message)
	{
		super(message);
	}

}
