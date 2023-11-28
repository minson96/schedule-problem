package com.example.schedule.cs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.schedule.cs.entity.Customer;
import com.example.schedule.cs.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping("/show")
	public String showCalculatedTime(Model model) {
		List<Customer> customerFIFOList = customerService.showFIFOCustomerList();
		int FIFOTotalTime = customerService.sumUseTime();
		List<Customer> customerSJFList = customerService.showSJFCustomerList();
		int SJFTotalTime = customerService.sumUseTime();
		model.addAttribute("customerFIFOList", customerFIFOList);
		model.addAttribute("FIFOTotalTime", FIFOTotalTime);
		model.addAttribute("customerSJFList", customerSJFList);
		model.addAttribute("SJFTotalTime", SJFTotalTime);
		return "api/show";
	}

	@GetMapping("/add")
	public String addCustomerInfo() {return "api/add";}

	@PostMapping("/add")
	public String addCustomerInfo(String name, int arriveTime, int useTime) {
		customerService.addCustomer(name, arriveTime, useTime);
		return "api/add";
	}

	@GetMapping("/delete")
	public String resetCustomerInfo() {
		customerService.resetCustomer();
		return "api/add";
	}
}
