package org.microservice.employee.service;

import java.util.List;

import org.microservice.employee.entity.Employee;
import org.microservice.employee.model.EmployeeObj;

public interface EmployeeService {

	List<EmployeeObj> findAllEmployee();

	Employee saveEmployee(EmployeeObj employee);

	EmployeeObj findByEmployeeId(Integer id);

	void deleteEmployee(Integer id);
}
