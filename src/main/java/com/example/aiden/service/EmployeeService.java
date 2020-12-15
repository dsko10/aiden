package com.example.aiden.service;

import com.example.aiden.model.Employee;
import com.example.aiden.model.EmployeeWithSalary;
import com.example.aiden.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeWithSalary getEmployeeWithSalary(Long id) {
        return withNetSalary(employeeRepository.findById(id));
    }

    public List<EmployeeWithSalary> getAllEmployeesWithSalary() {
        return employeeRepository.findAll().stream()
                .map(this::withNetSalary)
                .collect(Collectors.toList());
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id);
        String fullName = employee.getFirstName() + " " + employee.getLastName();
        employeeRepository.deleteById(id);
        return fullName;
    }

    private EmployeeWithSalary withNetSalary(Employee employee) {
        EmployeeWithSalary employeeWithSalary = new EmployeeWithSalary();
        employeeWithSalary.setId(employee.getId());
        employeeWithSalary.setFirstName(employee.getFirstName());
        employeeWithSalary.setLastName(employee.getLastName());
        employeeWithSalary.setPosition(employee.getPosition());
        employeeWithSalary.setWorkTime(employee.getWorkTime());
        employeeWithSalary.setGrossSalary(employee.getGrossSalary());
        employeeWithSalary.setTax(employee.getGrossSalary() * 0.0626);
        employeeWithSalary.setPensionInsurance(employee.getGrossSalary() * 0.0976);
        employeeWithSalary.setDisabilityInsurance(employee.getGrossSalary() * 0.015);
        employeeWithSalary.setMedicalInsurance(employee.getGrossSalary() * 0.0246);
        employeeWithSalary.setHealthInsurance(employee.getGrossSalary() * 0.0776);
        employeeWithSalary.setNetSalary(netSalary(employeeWithSalary));
        return employeeWithSalary;
    }

    private double netSalary(EmployeeWithSalary employeeWithSalary) {
        return employeeWithSalary.getGrossSalary()
                - employeeWithSalary.getTax()
                - employeeWithSalary.getPensionInsurance()
                - employeeWithSalary.getDisabilityInsurance()
                - employeeWithSalary.getMedicalInsurance()
                - employeeWithSalary.getHealthInsurance();
    }
}
