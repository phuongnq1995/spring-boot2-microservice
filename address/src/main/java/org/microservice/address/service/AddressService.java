package org.microservice.address.service;


import org.microservice.address.model.AddressObj;

public interface AddressService {

	AddressObj findByEmployeeId(int number);

	AddressObj saveAddress(AddressObj addressObj);

	void deleteAddress(int employeeId);
}
