package org.microservice.employee.feign;


import org.microservice.employee.model.AddressObj;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;

@FeignClient("address-service")
@RibbonClient(name="address-service")
public interface AddressClient {

	@GetMapping("/address/{employeeId}")
	AddressObj getAddress(@PathVariable("employeeId") int employeeId);

	@PostMapping("/address")
	@Headers("Content-Type: application/json")
	AddressObj saveAddress(@RequestBody AddressObj addressObj);

	@DeleteMapping("/address/{employeeId}")
	void deleteAddress(@PathVariable("employeeId") int employeeId);
}
