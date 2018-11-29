package org.microservice.employee.controller;

import java.util.List;
import java.util.logging.Logger;

import org.microservice.employee.entity.Employee;
import org.microservice.employee.model.EmployeeObj;
import org.microservice.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	protected Logger logger = Logger.getLogger(EmployeeController.class.getName());

	@CrossOrigin
	@GetMapping("/employees")
	public List<EmployeeObj> findAll() {
		return employeeService.findAllEmployee();
	}

	@GetMapping(value = "/employees/{id}")
	public EmployeeObj getUser(@PathVariable("id") Integer id) {
		return employeeService.findByEmployeeId(id);
	}

	@CrossOrigin
	@PostMapping
	public EmployeeObj save(@RequestBody EmployeeObj employee) {
		Employee entity = employeeService.saveEmployee(employee);
		return employeeService.findByEmployeeId(entity.getId());
	}

	@CrossOrigin
	@DeleteMapping("/employees/{id}")
	public void delete(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
	}
}
