package com.example.aiden.service;

import com.example.aiden.model.Employee;
import com.example.aiden.model.EmployeeWithSalary;
import com.example.aiden.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .sorted(Comparator.comparing(Employee::getId))
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
        employeeWithSalary.setTax(twoDecimalPlaces(employee.getGrossSalary() * 0.0626));
        employeeWithSalary.setPensionInsurance(twoDecimalPlaces(employee.getGrossSalary() * 0.0976));
        employeeWithSalary.setDisabilityInsurance(twoDecimalPlaces(employee.getGrossSalary() * 0.015));
        employeeWithSalary.setMedicalInsurance(twoDecimalPlaces(employee.getGrossSalary() * 0.0246));
        employeeWithSalary.setHealthInsurance(twoDecimalPlaces(employee.getGrossSalary() * 0.0776));
        employeeWithSalary.setNetSalary(twoDecimalPlaces(netSalary(employeeWithSalary)));
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

    private double twoDecimalPlaces(double value) {
        return Double.parseDouble(String.format("%.2f", value));
    }
}
