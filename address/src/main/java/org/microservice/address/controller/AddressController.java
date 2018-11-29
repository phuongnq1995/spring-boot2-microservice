package org.microservice.address.controller;

import java.util.logging.Logger;

import org.microservice.address.model.AddressObj;
import org.microservice.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService; 

	protected Logger logger = Logger.getLogger(AddressController.class.getName());

	@GetMapping("/address/{employeeId}")
	public AddressObj findByNumber(@PathVariable("employeeId") int employeeId) {
		return addressService.findByEmployeeId(employeeId);
	}

	@PostMapping("/address")
	public AddressObj save(@RequestBody AddressObj addressObj) {;
		addressObj = addressService.saveAddress(addressObj);

		return addressObj;
	}

	@DeleteMapping("/address/{employeeId}")
	public void delete(@PathVariable("employeeId") int employeeId) {
		addressService.deleteAddress(employeeId);
	}
}
