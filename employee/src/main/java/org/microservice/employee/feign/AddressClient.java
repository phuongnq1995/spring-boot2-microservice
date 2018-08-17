package org.microservice.employee.feign;


import org.microservice.employee.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("address-service")
public interface AddressClient {

	@GetMapping("address/{number}")
    Address getAccounts(@PathVariable("number") int number);
}
