package com.example.schedule.cs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.schedule.cs.entity.Customer;
import com.example.schedule.cs.repository.CustomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final CustomRepository customRepository;

	public List<Customer> showFIFOCustomerList() {
		List<Customer> sortList = customRepository.findAllByOrderByArriveTimeAsc();
		int beforeArriveTime = 0;
		int beforeUseTime = 0;
		int beforeStartTime = 0;
		for (Customer customer: sortList) {
			int startTime;
			int waitTime;
			if (beforeArriveTime + beforeUseTime >= customer.getArriveTime()) {
				startTime = beforeStartTime + beforeUseTime;
				waitTime = startTime - customer.getArriveTime();
			} else {
				startTime = customer.getArriveTime();
				waitTime = 0;
			}
			customer.updateStartWaitTime(startTime, waitTime);
			beforeArriveTime = customer.getArriveTime();
			beforeUseTime = customer.getUseTime();
			beforeStartTime = customer.getStartTime();
		}
		return sortList;
	}

	public List<Customer> showSJFCustomerList() {
		List<Customer> sortList = customRepository.findAllByOrderByUseTimeAsc();
		int beforeArriveTime = 0;
		int beforeUseTime = 0;
		int beforeStartTime = 0;
		for (Customer customer: sortList) {
			int startTime;
			int waitTime;
			if (beforeArriveTime + beforeUseTime >= customer.getArriveTime()) {
				startTime = beforeStartTime + beforeUseTime;
				waitTime = startTime - customer.getArriveTime();
			} else {
				startTime = customer.getArriveTime();
				waitTime = 0;
			}
			customer.updateStartWaitTime(startTime, waitTime);
			beforeArriveTime = customer.getArriveTime();
			beforeUseTime = customer.getUseTime();
			beforeStartTime = customer.getStartTime();
		}
		return sortList;
	}

	public int sumUseTime() {
		List<Customer> customerList = customRepository.findAll();
		int sum = 0;
		for (Customer customer: customerList) {
			sum += customer.getWaitTime();
		}
		return sum;
	}

	public void addCustomer(String name, int arriveTime, int useTime) {
		Customer customerInfo = Customer.builder()
			.name(name)
			.arriveTime(arriveTime)
			.useTime(useTime)
			.build();
		customRepository.save(customerInfo);
	}

	public void resetCustomer() {
		List<Customer> deleteCustomer = customRepository.findAll();
		customRepository.deleteAll(deleteCustomer);
	}
}
