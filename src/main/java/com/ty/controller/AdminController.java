package com.ty.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ty.dto.OrderDto;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Address;
import com.ty.entity.Cargo;
import com.ty.entity.Carrier;
import com.ty.entity.Driver;
import com.ty.entity.Loading;
import com.ty.entity.Orders;
import com.ty.entity.Truck;
import com.ty.entity.Unloading;
import com.ty.entity.Users;
import com.ty.service.AddressService;
import com.ty.service.CargoService;
import com.ty.service.CarrierService;
import com.ty.service.DriverService;
import com.ty.service.LoadingService;
import com.ty.service.OrderService;
import com.ty.service.TruckService;
import com.ty.service.UnloadingService;
import com.ty.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AddressService addressService;
	@Autowired
	UserService userService;
	@Autowired
	TruckService truckService;
	@Autowired
	CargoService cargoService;
	@Autowired
	CarrierService carrierService;
	@Autowired
	DriverService driverService;
	@Autowired
	LoadingService loadingService;
	@Autowired
	UnloadingService unloadingService;
	@Autowired
	OrderService orderService;

	// saving operation

	@PostMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address,@CookieValue(name = "role") String role) {
		return addressService.saveAddress(address,role);
	}

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<Users>> saveUser(@RequestBody Users users,@CookieValue(name = "role") String role) {
		return userService.saveUser(users,role);
	}

	@PostMapping("/truck")
	public ResponseEntity<ResponseStructure<Truck>> saveTruck(@RequestBody Truck truck, @CookieValue(name = "role") String role) {
		return truckService.saveTruck(truck,role);
	}

	@PostMapping("/cargo")
	public ResponseEntity<ResponseStructure<Cargo>> saveCargo(@RequestBody Cargo cargo,@CookieValue(name = "role") String role) {
		return cargoService.saveCargo(cargo,role);
	}

	@PostMapping("/carriers")
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@RequestBody Carrier carrier,@CookieValue(name = "role") String role) {
		return carrierService.saveCarrier(carrier,role);
	}

	@PostMapping("/driver")
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@RequestBody Driver driver,@CookieValue(name = "role") String role) {
		return driverService.saveDriver(driver,role);
	}

	@PostMapping("/loading")
	public ResponseEntity<ResponseStructure<Loading>> saveLoading(@RequestBody Loading loading,@CookieValue(name = "role") String role) {
		return loadingService.saveLoading(loading,role);
	}

	@PostMapping("/unloading")
	public ResponseEntity<ResponseStructure<Unloading>> saveUnloading(@RequestBody Unloading unloading,@CookieValue(name = "role") String role) {
		return unloadingService.saveUnLoading(unloading,role);
	}

	@PostMapping("/orders")
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(@RequestBody OrderDto order,@CookieValue(name = "role") String role) {
		return orderService.saveOrder(order,role);
	}

	// update operation

	@PutMapping("/orders")
	public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody OrderDto order,@CookieValue(name = "role") String role) {
		return orderService.saveOrder(order,role);
	}

	@PutMapping("/carrier")
	public ResponseEntity<ResponseStructure<Carrier>> updateCarrier(@RequestBody Carrier carrier,@CookieValue(name = "role") String role) {
		return carrierService.saveCarrier(carrier,role);
	}

	@PutMapping("/loading")
	public ResponseEntity<ResponseStructure<Loading>> updateLoading(@RequestBody Loading loading,@CookieValue(name = "role") String role) {
		return loadingService.saveLoading(loading,role);
	}

	@PutMapping("/unloading")
	public ResponseEntity<ResponseStructure<Unloading>> updateUnloading(@RequestBody Unloading unloading,@CookieValue(name = "role") String role) {
		return unloadingService.saveUnLoading(unloading,role);
	}

	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<Users>> updateUser(@RequestBody Users user,@CookieValue(name = "role") String role) {
		return userService.saveUser(user,role);
	}

	// ViewAll orders

	@GetMapping("/orders")
	public ResponseEntity<ResponseStructure<List<Orders>>> findAllOrder() {
		return orderService.findAll();
	}

	// delete the order
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(@PathVariable int id) {
		return orderService.deleteOrders(id);
	}

	// ViewAllUser
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<Users>>> findAllUsers() {
		return userService.findAll();
	}

	// DeleteUser
	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<Users>> delete(@PathVariable int id) {
		return userService.delete(id);
	}

}