package org.microservice.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.microservice.employee.feign.AddressClient;
import org.microservice.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	private AddressClient addressClient;

	protected Logger logger = Logger.getLogger(EmployeeController.class.getName());
	
	private List<Employee> employees;

	public EmployeeController() {
		employees = new ArrayList<>();
		employees.add(new Employee(1, "employee 1", 25));
		employees.add(new Employee(2, "employee 2", 26));
		employees.add(new Employee(3, "employee 3", 27));
		employees.add(new Employee(4, "employee 4", 28));
	}

	@CrossOrigin
	@GetMapping("/employees")
	public List<Employee> findAll() {
		for(Employee e : employees) {
			e.setAddress(addressClient.getAccounts(e.getId()));
		}
		logger.info("Employee.findAll()");
		return employees;
	}
}
