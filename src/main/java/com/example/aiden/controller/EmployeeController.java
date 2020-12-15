package com.example.aiden.controller;

import com.example.aiden.model.Employee;
import com.example.aiden.service.EmployeeService;
import com.example.aiden.service.WorkTimeProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private final WorkTimeProvider workTimeProvider;

    public EmployeeController(EmployeeService employeeService, WorkTimeProvider workTimeProvider) {
        this.employeeService = employeeService;
        this.workTimeProvider = workTimeProvider;
    }

    @GetMapping("/employees")
    public String getAllEmployees(
            Model model,
            @RequestParam(required = false, value = "added") String added,
            @RequestParam(required = false, value = "updated") String updated,
            @RequestParam(required = false, value = "deleted") String deleted
    ) {
        model.addAttribute("employees", employeeService.getAllEmployeesWithSalary());
        return "employeesList";
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeWithSalary(id));
        return "employeeProfile";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployee(@PathVariable Long id, @ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        model.addAttribute("workTimeEntries", workTimeProvider.getWorkTimeEntriesAsMap());
        return "employeeForm";
    }

    @GetMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", employee);
        model.addAttribute("workTimeEntries", workTimeProvider.getWorkTimeEntriesAsMap());
        return "employeeForm";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee) {
        boolean isNew = employee.getId() == null;
        Employee saved = employeeService.saveEmployee(employee);
        String fullName = saved.getFirstName() + " " + saved.getLastName();
        if (isNew) {
            return "redirect:/employees?added=" + fullName;
        } else {
            return "redirect:/employees?updated=" + fullName;
        }
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        String fullName = employeeService.deleteEmployee(id);
        return "redirect:/employees?deleted=" + fullName;
    }
}
