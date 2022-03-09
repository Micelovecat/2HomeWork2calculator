package com.skypro.calculator.services.impl;

import com.skypro.calculator.exceptions.EmployeeNotFoundException;
import com.skypro.calculator.model.Employee;
import com.skypro.calculator.services.DepartmentService;
import com.skypro.calculator.services.EmployeeMapService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeMapService employeeService;

    public DepartmentServiceImpl(EmployeeMapService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalaryByDepartmentId(int departmentId) {
        return employeeService.getAllEmployees().stream().
                filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found - "));
    }

    @Override
    public Employee findEmployeeWithMinSalaryByDepartmentId(int departmentId) {
        return  employeeService.getAllEmployees().stream().
                filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found - "));
    }

    @Override
    public Collection<Employee> findEmployeesByDepartmentId(int departmentId) {
        return employeeService.getAllEmployees().stream().
               filter(e -> e.getDepartmentId() == departmentId).
               collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllEmployeesByDepartmentId() {
        return employeeService.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
