package com.skypro.calculator;

import com.skypro.calculator.exceptions.EmployeeExistsException;
import com.skypro.calculator.exceptions.EmployeeNotFoundException;
import com.skypro.calculator.exceptions.InvalidNameException;
import com.skypro.calculator.exceptions.ZeroDividerException;
import com.skypro.calculator.model.Employee;
import com.skypro.calculator.services.impl.EmployeeServiceMapImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.skypro.calculator.CalculatorServiceImplTestConstants.*;
import static com.skypro.calculator.EmployeeTestConstants.*;
import static com.skypro.calculator.EmployeeTestConstants.WRONG_FIRST_NAME;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    private EmployeeServiceMapImpl out = new EmployeeServiceMapImpl();

    @BeforeEach
    private void initialize(){
        out = new EmployeeServiceMapImpl();
    }
    @Test
    public void shouldAddEmployeeWhenTheyDontExist(){
        Employee expectedEmployee = new Employee(FIRST_NAME, LAST_NAME);
        assertEquals(0, out.getAllEmployees().size());
        Employee actualEmployee = out.addEmployee(FIRST_NAME, LAST_NAME);
        assertEquals(expectedEmployee, actualEmployee);
        assertEquals(1, out.getAllEmployees().size());
        assertTrue(out.getAllEmployees().contains(expectedEmployee));
    }

    private static Stream<Arguments> provideArgumentsForWrongNameEmployeeTests(){
        return Stream.of(
                Arguments.of(FIRST_NAME, WRONG_LAST_NAME),
                Arguments.of(WRONG_FIRST_NAME, LAST_NAME),
                Arguments.of(WRONG_FIRST_NAME, WRONG_FIRST_NAME)
        );
    }

    @MethodSource("provideArgumentsForWrongNameEmployeeTests")
    @ParameterizedTest
    public void shouldThrowInvalidNameExceptionWhenNameIsIncorrect(String firstName, String lastName){
        assertThrows(InvalidNameException.class, () -> out.addEmployee(firstName, lastName));
    }

    @Test
    public void shouldThrowEmployeeExistsExceptionWhenNullHeExists(){
        Employee existedEmployee = out.addEmployee(FIRST_NAME, LAST_NAME);
        assertTrue(out.getAllEmployees().contains(existedEmployee));
        assertThrows(EmployeeExistsException.class, () -> out.addEmployee(FIRST_NAME, LAST_NAME));
        assertEquals(1, out.getAllEmployees().size());
    }

    @Test
    public void shouldFindEmployeeWhenHeExists(){
        Employee existedEmployee = out.addEmployee(FIRST_NAME, LAST_NAME);
        assertEquals(existedEmployee, out.findEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenEmployeeDoesntExist(){
        assertEquals(0, out.getAllEmployees().size());
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee(FIRST_NAME, LAST_NAME));
        assertEquals(0, out.getAllEmployees().size());
    }

    @Test
    public void shouldRemoveEmployeeWhenHeExists(){
        Employee existedEmployee = out.addEmployee(FIRST_NAME, LAST_NAME);
        assertEquals(1, out.getAllEmployees().size());
        Employee actualEmployee = out.removeEmployee(FIRST_NAME, LAST_NAME);
        assertEquals(existedEmployee, actualEmployee);
        assertTrue(out.getAllEmployees().isEmpty());
        assertFalse(out.getAllEmployees().contains(existedEmployee));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemovingEmployeeDoesntExist(){
        assertTrue(out.getAllEmployees().isEmpty());
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldReturnEmptyCollectionWhenEmployeesDontExist(){
        assertTrue(out.getAllEmployees().isEmpty());

    }

    @Test
    public void shouldReturnListOfEmployeesWhenEmployeesTheyExist(){
        Employee firstEmployee = out.addEmployee(FIRST_NAME, LAST_NAME);
        Employee secondEmployee = out.addEmployee(FIRST_NAME2, LAST_NAME2);
        Collection<Employee> expected = List.of(firstEmployee, secondEmployee);
        assertIterableEquals(expected, out.getAllEmployees());
    }

}
