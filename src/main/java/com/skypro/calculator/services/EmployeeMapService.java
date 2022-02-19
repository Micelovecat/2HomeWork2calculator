package com.skypro.calculator.services;

import com.skypro.calculator.model.Employee;

import java.util.Collection;
import java.util.Set;

public interface EmployeeMapService extends EmployeeService {
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> getAllEmployees();
}
