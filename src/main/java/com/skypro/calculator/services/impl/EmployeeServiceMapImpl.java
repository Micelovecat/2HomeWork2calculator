package com.skypro.calculator.services.impl;

import com.skypro.calculator.exceptions.EmployeeExistsException;
import com.skypro.calculator.exceptions.EmployeeNotFoundException;
import com.skypro.calculator.exceptions.InvalidNameException;
import com.skypro.calculator.model.Employee;
import com.skypro.calculator.services.EmployeeMapService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceMapImpl implements EmployeeMapService {

    private Map<String, Employee> employees = new HashMap<>();

//    private String keyName(String firstName, String lastName) {
//
//        String key = firstName + lastName;
//        return key;
//    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        validateNames(firstName, lastName);

        String key = getKey(firstName, lastName);



        Employee addingEmployee = new Employee(key.split("-")[0], key.split("-")[1]);


//        String key = keyName(correctedFirstName, correctedLastName);
        if (employees.containsKey(key)) {
            throw new EmployeeExistsException("This employee has been already added");
        }
        employees.put(key, addingEmployee);
        return addingEmployee;
    }

    private String getKey(String firstName, String lastName) {
        String correctedFirstName = capitalize(firstName.toLowerCase());
        String correctedLastName = capitalize(lastName.toLowerCase());
        return correctedFirstName + "_" + correctedLastName;
    }

    private void validateNames(String... names) {
        Arrays.stream(names).forEach(name -> {
            if (!isAlpha(name)){
                throw new InvalidNameException("Invalid name!");
            }
        });
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateNames(firstName, lastName);

        String key = getKey(firstName, lastName);

//        String key = keyName(correctedFirstName, correctedLastName);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("This employee can`t be found");
        }
        return employees.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateNames(firstName, lastName);

        String key = getKey(firstName, lastName);

//        String key = keyName(correctedFirstName, correctedLastName);
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
