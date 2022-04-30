package com.maw.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maw.api.model.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long> {

}
