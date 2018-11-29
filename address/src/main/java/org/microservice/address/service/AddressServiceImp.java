package org.microservice.address.service;

import org.microservice.address.entity.Address;
import org.microservice.address.model.AddressObj;
import org.microservice.address.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImp implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private Environment environment;

	@Override
	public AddressObj findByEmployeeId(int number) {
		Address address = addressRepository.findByEmployeeId(number);
		AddressObj addressObj = new AddressObj();

		BeanUtils.copyProperties(address, addressObj);
		addressObj.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return addressObj;
	}

	@Override
	public AddressObj saveAddress(AddressObj addressObj) {
		Address address = new Address();

		BeanUtils.copyProperties(addressObj, address);

		addressRepository.save(address);

		return addressObj;
	}

	@Override
	@Transactional
	public void deleteAddress(int employeeId) {
		addressRepository.deleteByEmployeeId(employeeId);
	}

}
