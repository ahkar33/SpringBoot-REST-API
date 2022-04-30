package com.maw.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maw.api.exception.ResourceNotFoundException;
import com.maw.api.model.Employee;
import com.maw.api.repository.EmployeeRespository;
import com.maw.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRespository employeeResposity;

	public EmployeeServiceImpl(EmployeeRespository employeeResposity) {
		super();
		this.employeeResposity = employeeResposity;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeResposity.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeResposity.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeResposity.findById(id);
//		if (employee.isPresent()) {
//			return employee.get();
//		} else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}

		return employeeResposity.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// check whether employee with given id exist or not
		Employee existingEmployee = employeeResposity.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to DB
		employeeResposity.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		// check whether a employee exists in DB or not
		employeeResposity.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		employeeResposity.deleteById(id);
	}

}
