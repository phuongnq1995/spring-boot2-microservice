package org.microservice.address.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.microservice.address.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

	@Autowired
	private Environment environment;

	private List<Address> address;

	protected Logger logger = Logger.getLogger(AddressController.class.getName());

	public AddressController() {
		address = new ArrayList<>();
		address.add(new Address(1, "11111"));
		address.add(new Address(2, "22222"));
		address.add(new Address(3, "33333"));
		address.add(new Address(4, "44444"));
		address.add(new Address(5, "55555"));
		address.add(new Address(6, "66666"));
	}

	@GetMapping("address/{number}")
	public Address findByNumber(@PathVariable("number") int number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
		Address address = this.address.stream().filter(it -> it.getNumber() == (number)).findFirst().get();
		address.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return address;
	}
}
