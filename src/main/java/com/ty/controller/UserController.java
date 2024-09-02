package com.ty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ty.dto.OrderDto;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Loading;
import com.ty.entity.Orders;
import com.ty.entity.Unloading;
import com.ty.service.LoadingService;
import com.ty.service.OrderService;
import com.ty.service.UnloadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	OrderService orderService;
	@Autowired
	LoadingService loadingService;
	@Autowired
	UnloadingService unloadingService;

	@PostMapping("/orders")
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(@RequestBody OrderDto order, @CookieValue String role) {
		return orderService.saveOrder2(order, role);
	}

	@PutMapping("/orders")
	public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody OrderDto order,
			@CookieValue String role) {
		return orderService.saveOrder2(order, role);
	}

	@PutMapping("/loading")
	public ResponseEntity<ResponseStructure<Loading>> loading(@RequestBody Loading loading, @CookieValue String role) {
		return loadingService.saveLoading(loading, role);
	}

	@PutMapping("/unloading")
	public ResponseEntity<ResponseStructure<Unloading>> unloading(@RequestBody Unloading unloading,
			@CookieValue String role) {
		return unloadingService.saveUnLoading(unloading, role);
	}

}
