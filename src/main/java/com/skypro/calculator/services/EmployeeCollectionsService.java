package com.skypro.calculator.services;

import com.skypro.calculator.model.Employee;

import java.util.Set;

public interface EmployeeCollectionsService {
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Set<Employee> getAllEmployees();
}
