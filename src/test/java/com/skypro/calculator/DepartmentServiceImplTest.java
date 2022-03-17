package com.skypro.calculator;

import com.skypro.calculator.exceptions.EmployeeNotFoundException;
import com.skypro.calculator.services.EmployeeMapService;
import com.skypro.calculator.services.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.skypro.calculator.EmployeeTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {


    @Mock
    private EmployeeMapService employeeMapService;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void shouldFindEmployeeWithMaxSalaryByDepartmentId(){
        when(employeeMapService.getAllEmployees()).thenReturn(EMPLOYEES);
        assertEquals(MAX_SALARY_EMPLOYEE, out.findEmployeeWithMaxSalaryByDepartmentId(DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenNoMaxSalary(){
        when(employeeMapService.getAllEmployees()).thenReturn(new ArrayList());
        assertThrows(EmployeeNotFoundException.class, () ->
                out.findEmployeeWithMaxSalaryByDepartmentId(DEPARTMENT_ID));
    }

    @Test
    public void shouldFindEmployeeWithMinSalaryByDepartmentId(){
        when(employeeMapService.getAllEmployees()).thenReturn(EMPLOYEES);
        assertEquals(MIN_SALARY_EMPLOYEE, out.findEmployeeWithMinSalaryByDepartmentId(DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenNoMinSalary(){
        when(employeeMapService.getAllEmployees()).thenReturn(new ArrayList());
        assertThrows(EmployeeNotFoundException.class, () ->
                out.findEmployeeWithMinSalaryByDepartmentId(DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnEmptyMapWhenEmployeeDontExist(){
        when(employeeMapService.getAllEmployees()).thenReturn(new ArrayList());
        assertEquals(new HashMap(), out.findAllEmployeesByDepartmentId());
    }

    @Test
    public void shouldReturnAllEmployeesByDepartmentIdWhenEmployeesExist(){
        when(employeeMapService.getAllEmployees()).thenReturn(DIFFERENT_DEPARTMENTS_EMPLOYEES);
        assertEquals(DEPARTMENT_MAP, out.findAllEmployeesByDepartmentId());
    }

    @Test
    public void shouldReturnEmptyCollectionWhenNoEmployeesExistDepartment(){
        when(employeeMapService.getAllEmployees()).thenReturn(EMPLOYEES);
        assertEquals(new ArrayList(), out.findEmployeesByDepartmentId(DEPARTMENT_ID2));
    }

    @Test
    public void shouldReturnEmployeesByDepartmentWhenEmployeesExistThere(){
        when(employeeMapService.getAllEmployees()).thenReturn(DIFFERENT_DEPARTMENTS_EMPLOYEES);
        assertEquals(List.of(MAX_SALARY_EMPLOYEE),
                out.findEmployeesByDepartmentId(DEPARTMENT_ID));
        assertEquals(List.of(OTHER_DEPARTMENT_EMPLOYEE),
                out.findEmployeesByDepartmentId(DEPARTMENT_ID2));
    }


}
