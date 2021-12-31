package com.customerRelationship.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.customerRelationship.entity.Customer;
import com.customerRelationship.service.customerService;

@Controller
@RequestMapping("/customers")
public class customerController {
	
	@Autowired
	private customerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("Customers", customers);
		return "list-customers";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer newCustomer = new Customer();
		model.addAttribute("Customer", newCustomer);
		return "form-customers";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerID, Model model) {
		Customer newCustomer = customerService.findById(customerID);
		model.addAttribute("Customer", newCustomer);
		return "form-customers";
	}
	
	@PostMapping("/save")
	public String saveCustomerData(@RequestParam("id") int customerID, 
			@RequestParam("firstname") String firstName, 
			@RequestParam("lastname") String lastName,
			@RequestParam("email") String email) {
		System.out.println(customerID);
		Customer newCustomer;
		if(customerID != 0) {
			newCustomer = customerService.findById(customerID);
			newCustomer.setFirstName(firstName);
			newCustomer.setLastName(lastName);
			newCustomer.setEmail(email);
		}else {
			newCustomer = new Customer(customerID, firstName, lastName, email);
		}
		customerService.saveCustomer(newCustomer);
		return "redirect:/customers/list";
	}
	
	@RequestMapping("/delete")
	public String deleteCustomerData(@RequestParam("customerId") int customerID) {
		customerService.deleteById(customerID);
		return "redirect:/customers/list";
	}
}