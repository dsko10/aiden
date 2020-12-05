package com.example.aiden.controller;

import com.example.aiden.model.Employee;
import com.example.aiden.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getAllEmployees(
            Model model,
            @RequestParam(required = false, value = "added") String added,
            @RequestParam(required = false, value = "updated") String updated,
            @RequestParam(required = false, value = "deleted") String deleted
    ) {
        if (added != null) model.addAttribute("added", added);
        if (updated != null) model.addAttribute("updated", updated);
        if (deleted != null) model.addAttribute("deleted", deleted);
        model.addAttribute("employees", employeeService.getAllEmployeesWithSalary());
        return "employeesList";
    }

    @GetMapping("/employees/{id}")
    public String getAllEmployees(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeWithSalary(id));
        return "employeeProfile";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployee(@PathVariable Long id, @ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employeeForm";
    }

    @GetMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "employeeForm";
    }

    @PutMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee) {
        Employee saved = employeeService.saveEmployee(employee);
        String fullName = saved.getFirstName() + " " + saved.getLastName();
        if (employee.getId() == null) {
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
