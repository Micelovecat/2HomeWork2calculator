package com.skypro.calculator.services.impl;


import com.skypro.calculator.exceptions.EmployeeExistsException;
import com.skypro.calculator.exceptions.EmployeeNotFoundException;
import com.skypro.calculator.model.Employee;
import com.skypro.calculator.services.EmployeeCollectionsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceCollectionsImpl implements EmployeeCollectionsService {

        private Set<Employee> employees = new HashSet<>();

        @Override
        public Employee addEmployee(String firstName, String lastName) {

            Employee addingEmployee = new Employee(firstName, lastName);

            if (employees.contains(addingEmployee)){
                throw new EmployeeExistsException("This employee has been already added");
            }
            employees.add(addingEmployee);
            return addingEmployee;
        }

        @Override
        public Employee removeEmployee(String firstName, String lastName) {
            Employee removingEmployee = new Employee(firstName, lastName);

            if (!employees.remove(removingEmployee)){
                throw new EmployeeNotFoundException("This employee can`t be found");
            }
            return removingEmployee;
        }

        @Override
        public Employee findEmployee(String firstName, String lastName) {
            Employee findingEmployee = new Employee(firstName, lastName);

            if (!employees.contains(findingEmployee)){
                throw new EmployeeNotFoundException("This employee can`t be found");
            }
            return findingEmployee;
        }

    @Override
    public Set<Employee> getAllEmployees() {
        return employees;
    }
}
