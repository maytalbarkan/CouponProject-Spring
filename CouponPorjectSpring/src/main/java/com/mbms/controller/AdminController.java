package com.mbms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.login.LoginController;
import com.mbms.login.Session;
import com.mbms.model.Company;
import com.mbms.model.Customer;
import com.mbms.service.AdminService;
import com.mbms.service.AdminServiceImpl;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private Map<String, Session> tokens;
	
	private Session exists (String token) {
		return LoginController.tokens.get(token);
	}
	//COMPANY:
	
	@GetMapping("/getAllCompnies")
	public ResponseEntity<List<Company>> getAllCompany(){
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(adminService.allCompanies(), HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/getCompany/{id}")
	public Company companyById(@PathVariable int id) {
		return adminService.companyById(id);
	}
	
	@PostMapping("/createCompany/{token}")
	public ResponseEntity<String> createCompany (@RequestBody Company company, @PathVariable String token) throws Exception{
		Session session = exists(token);
		System.out.println(session);
		if (session==null) {
			throw new Exception("wrong session");
		} else if(session!=null) {
			session.setLastAccesed(System.currentTimeMillis());
		try {
			
			((AdminServiceImpl)session.getFacade()).createCompany(company);
			return new ResponseEntity<>("company created", HttpStatus.OK);
			
	}catch (Exception e){
		return new ResponseEntity<>("wrong", HttpStatus.UNAUTHORIZED);
	}
	}
		return null;
		
	}
	//not working
	@PostMapping("/updateCompany")
	public ResponseEntity<Company> updateCompany(@RequestParam int id, @RequestParam String password, @RequestParam String email) {
		Company company = null;
		company = adminService.companyById(id);
		if (company !=null) {
			adminService.updateCompany(company, password, email);
			ResponseEntity<Company> result = new ResponseEntity<>(company,HttpStatus.OK);
			return result;
		}
		return null;
	}
	
	@DeleteMapping ("/deleteCompany/{id}")
	public ResponseEntity<Company> deleteCompany (@PathVariable("id") int id){
		adminService.deleteCompany(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	//Customer
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(adminService.allCustomers(), HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/getCustomer/{id}")
	public Customer customerById(@PathVariable int id) {
		return adminService.customerById(id);
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer) throws Exception{
		Customer customer2 = adminService.createCustomer(customer);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer2,HttpStatus.OK);
		return result;
	}
//not work
	@PostMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestParam int id, @RequestParam String password){
		Customer customer = null;
		customer = adminService.customerById(id);
		if (customer != null) {
		//	adminService.updateCustomer(customer, password);
			ResponseEntity<Customer> result = new ResponseEntity<>(customer,HttpStatus.OK);
			return result;	
		}
		return null;
	}

	@DeleteMapping("/deleteCustomer/{id}")	
	public void deleteCustomer(@PathVariable int id) {
		 adminService.deleteCustomer(id);
	}
}
