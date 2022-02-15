package com.skypro.calculator.rest;

import com.skypro.calculator.model.Employee;
import com.skypro.calculator.services.EmployeeCollectionsService;
import com.skypro.calculator.services.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeCollectionsService employeeService;

    public EmployeeController(@Qualifier("employeeServiceCollectionsImpl") EmployeeCollectionsService employeeService) { this.employeeService = employeeService;  }

    @GetMapping("/")
    public String greetEmployee(){
        return "Hello employees!";
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName){
        Employee addedEmployee = employeeService.addEmployee(firstName, lastName);
        return addedEmployee;
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName){
        Employee removedEmployee = employeeService.removeEmployee(firstName, lastName);
        return removedEmployee;
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName){
        Employee foundEmployee = employeeService.findEmployee(firstName, lastName);
        return foundEmployee;
    }

    @GetMapping("/getAllEmployees")
    public Collection<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
