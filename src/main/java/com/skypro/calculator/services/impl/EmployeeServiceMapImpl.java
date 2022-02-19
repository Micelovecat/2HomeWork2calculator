package com.skypro.calculator.services.impl;

import com.skypro.calculator.exceptions.EmployeeExistsException;
import com.skypro.calculator.exceptions.EmployeeNotFoundException;
import com.skypro.calculator.model.Employee;
import com.skypro.calculator.services.EmployeeCollectionsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceMapImpl implements EmployeeCollectionsService {

    private Map<String, Employee> employees = new HashMap<>();

    private String keyName(String firstName, String lastName) {
        String key = firstName + lastName;
        return key;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        Employee addingEmployee = new Employee(firstName, lastName);
        String key = keyName(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeExistsException("This employee has been already added");
        }

        employees.put(key, addingEmployee);
        return addingEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = keyName(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("This employee can`t be found");
        }
        return employees.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee findingEmployee = new Employee(firstName, lastName);
        String key = keyName(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("This employee can`t be found");
        }
        return employees.get(key);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
