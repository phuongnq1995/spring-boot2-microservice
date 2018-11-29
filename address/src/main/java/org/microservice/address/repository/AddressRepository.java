package org.microservice.address.repository;

import org.microservice.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	Address findByEmployeeId(Integer employeeId);

	void deleteByEmployeeId(Integer employeeId);

}
