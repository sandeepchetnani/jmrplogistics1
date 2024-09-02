package com.ty.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.entity.Orders;
import com.ty.repository.OrderRepository;

@Repository
public class OrderDao {

	@Autowired
	private OrderRepository orderRepository;

	public Orders saveOrder(Orders order) {
		return orderRepository.save(order);
	}

	public List<Orders> findAllOrder() {
		return orderRepository.findAll();
	}

	public void deleteOrder(int id) {
		orderRepository.deleteById(id);
	}

	public Optional<Orders> findbyId(int id) {
		return orderRepository.findById(id);
	}
}
