package org.microservice.employee.service;

import java.util.ArrayList;
import java.util.List;
import org.microservice.employee.entity.Employee;
import org.microservice.employee.feign.AddressClient;
import org.microservice.employee.model.AddressObj;
import org.microservice.employee.model.EmployeeObj;
import org.microservice.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressClient addressClient;

	public List<EmployeeObj> findAllEmployee(){
		List<EmployeeObj> employeeObjs = new ArrayList<>();
		List<Employee> employees = employeeRepository.findAll();
		
		EmployeeObj employeeObj = null;
		for(Employee e : employees) {
			employeeObj = new EmployeeObj();

			BeanUtils.copyProperties(e, employeeObj);

			employeeObj.setAddressObj(addressClient.getAddress(e.getId()));
			
			employeeObjs.add(employeeObj);
		}
		return employeeObjs;
	}

	@Override
	@Transactional
	public Employee saveEmployee(EmployeeObj employeeObj) {
		Employee employee = new Employee();
		
		BeanUtils.copyProperties(employeeObj, employee);

		employee = employeeRepository.save(employee);

		System.out.println("employee.getId():"+employee.getId());
		employeeObj.getAddressObj().setEmployeeId(employee.getId());

		System.out.println("123123:"+employeeObj.getAddressObj().getName());

		addressClient.saveAddress(employeeObj.getAddressObj());
		
		return employee;
	}

	@Override
	@Transactional
	public EmployeeObj findByEmployeeId(Integer id) {
		EmployeeObj employee = new EmployeeObj();
		Employee entity = employeeRepository.getOne(id);

		BeanUtils.copyProperties(entity, employee);

		AddressObj address = addressClient.getAddress(id);
		
		employee.setAddressObj(address);

		return employee;
	}

	@Override
	public void deleteEmployee(Integer id) {
		addressClient.deleteAddress(id);
		employeeRepository.deleteById(id);
	}

}
