package com.example.aiden.repository;

import com.example.aiden.model.Employee;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Long> {
    Employee findById(Long id);

    List<Employee> findAll();

    Employee save(Employee employee);

    void deleteById(Long id);
}
