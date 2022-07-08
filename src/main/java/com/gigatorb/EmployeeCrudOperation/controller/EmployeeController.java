package com.gigatorb.EmployeeCrudOperation.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gigatorb.EmployeeCrudOperation.domain.Employee;
import com.gigatorb.EmployeeCrudOperation.exception.ResourceNotFoundException;
import com.gigatorb.EmployeeCrudOperation.repository.EmployeeRepository;

//@CrossOrigin(origins ="http://employeecrudbucket.s3-website.us-east-2.amazonaws.com/")
@CrossOrigin(origins ="http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
    private EmployeeRepository emprepo;
	
	//get all employee
	@GetMapping("/employee")
	public List<Employee> getAllEmployees(){
		System.out.println("Hello Jenkins anupam here");
		return emprepo.findAll();
	}
	
	// create employee rest api
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return emprepo.save(employee);
	}
	
	// get employee by id rest api
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		
		Employee employee = emprepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: " + id)); 
		
		return ResponseEntity.ok(employee);
	}
	
	// update employee rest api
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetail){
		
		Employee employee = emprepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: "+id )); 
		
		employee.setFirstname(employeeDetail.getFirstname());
		employee.setLastname(employeeDetail.getLastname());
		employee.setEmail(employeeDetail.getEmail());
		
		Employee updateEmployee= emprepo.save(employee);
		
		return ResponseEntity.ok(updateEmployee);
	}
	
	// delete employee rest api
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = emprepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: "+id ));
		
		emprepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
			

	}


}
