package com.ty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.dao.OrderDao;
import com.ty.dto.OrderDto;
import com.ty.dto.ResponseStructure;
import com.ty.entity.Orders;
import com.ty.entity.Users;
import com.ty.exception.OrderNotFoundException;
import com.ty.repository.CargoRepository;
import com.ty.repository.CarriersRepository;
import com.ty.repository.LoadingRepository;
import com.ty.repository.UnloadingRepository;
import com.ty.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;
	@Autowired
	LoadingRepository loadingRepository;

	@Autowired
	UnloadingRepository unloadingRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CargoRepository cargoRepository;

	@Autowired
	CarriersRepository carriersRepository;

	public ResponseEntity<ResponseStructure<Orders>> saveOrder(@Valid OrderDto orderdto, String role) {
		ResponseStructure<Orders> responseStructure = new ResponseStructure<>();
		if (role.equals("Admin")) {
			Orders orderToBeSaved = new Orders();
			orderToBeSaved.setId(orderdto.getId());
			orderToBeSaved.setOrderStatus(orderdto.getOrderStatus());
			orderToBeSaved.setFreightCost(orderdto.getFreightCost());
			orderToBeSaved.setAdditionalInfo(orderdto.getAdditionalInfo());
			orderToBeSaved.setDateOfOrder(orderdto.getDateOfOrder());
			orderToBeSaved.setLoading(loadingRepository.findById(orderdto.getLoadingId()).get());
			orderToBeSaved.setCargo(cargoRepository.findById(orderdto.getCargiId()).get());
			orderToBeSaved.setCarrier(carriersRepository.findById(orderdto.getCarrierId()).get());
			orderToBeSaved.setUnloading(unloadingRepository.findById(orderdto.getUnloadId()).get());
			List<Users> loadUser = new ArrayList<>();
			loadUser.add(userRepository.findById(orderdto.getLoaduserId()).get());
			orderToBeSaved.setLoadingUser(loadUser);
			List<Users> UnloadUser = new ArrayList<>();
			UnloadUser.add(userRepository.findById(orderdto.getUnloadId()).get());
			orderToBeSaved.setUnloadingUser(UnloadUser);
			orderDao.saveOrder(orderToBeSaved);

			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(orderToBeSaved);
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.CREATED);
		} else {
			responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
			responseStructure.setData(null);
			responseStructure.setMessage("u r not a admin ....");
			return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.FORBIDDEN);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Orders>> saveOrder2(@Valid OrderDto orderDto,String role)
	{
		ResponseStructure<Orders> responseStructure = new ResponseStructure<>();
		if (role.equals("User")) {
			Orders orderToBeSaved = new Orders();
			orderToBeSaved.setId(orderDto.getId());
			orderToBeSaved.setOrderStatus(orderDto.getOrderStatus());
			orderToBeSaved.setFreightCost(orderDto.getFreightCost());
			orderToBeSaved.setAdditionalInfo(orderDto.getAdditionalInfo());
			orderToBeSaved.setDateOfOrder(orderDto.getDateOfOrder());
			orderToBeSaved.setCargo(cargoRepository.findById(orderDto.getCargiId()).get());
			orderToBeSaved.setCarrier(carriersRepository.findById(orderDto.getCarrierId()).get());
			orderDao.saveOrder(orderToBeSaved);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(orderToBeSaved);
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.CREATED);
		}
		else {
			responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
			responseStructure.setData(null);
			responseStructure.setMessage("u r not a user ....");
			return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.FORBIDDEN);
		}
	}
	

	public ResponseEntity<ResponseStructure<List<Orders>>> findAll() {
		List<Orders> list = orderDao.findAllOrder();
		if (list == null) {
			throw new OrderNotFoundException("order not available");
		}
		ResponseStructure<List<Orders>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setData(list);
		responseStructure.setMessage("Success");
		return new ResponseEntity<ResponseStructure<List<Orders>>>(responseStructure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Orders>> deleteOrders(@Valid int id) {
		Optional<Orders> find = orderDao.findbyId(id);
		ResponseStructure<Orders> responseStructure = new ResponseStructure<>();
		if (find.isPresent()) {
			orderDao.deleteOrder(id);
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setData(null);
			responseStructure.setMessage("deleted");
			return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.ACCEPTED);
		} else {
			throw new OrderNotFoundException("order id not present");
		}
	}

}
