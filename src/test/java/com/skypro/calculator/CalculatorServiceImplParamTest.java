package com.skypro.calculator;

import com.skypro.calculator.exceptions.ZeroDividerException;
import com.skypro.calculator.services.impl.CalculatorServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.skypro.calculator.CalculatorServiceImplTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceImplParamTest {

    private CalculatorServiceImpl out = new CalculatorServiceImpl();

    private static Stream<Arguments> provideArgumentsForCalculatorTests(){
        return Stream.of(
                Arguments.of(ONE, TWO),
                Arguments.of(ONE, MINUS_TWO),
                Arguments.of(ZERO, TWO),
                Arguments.of(ZERO, MINUS_TWO),
                Arguments.of(MINUS_ONE, MINUS_TWO)
        );
    }

    @MethodSource("provideArgumentsForCalculatorTests")
    @ParameterizedTest
    public void shouldReturnCorrectResultOfSum(int num1, int num2){
        assertEquals(num1 + num2, out.plus(num1, num2));
    }

    @MethodSource("provideArgumentsForCalculatorTests")
    @ParameterizedTest
    public void shouldReturnCorrectResultOfSubtraction(int num1, int num2){
        assertEquals(num1 - num2, out.minus(num1, num2));
    }

    @MethodSource("provideArgumentsForCalculatorTests")
    @ParameterizedTest
    public void shouldReturnCorrectResultOfMultiplying(int num1, int num2){
        assertEquals(num1 * num2, out.multiply(num1, num2));
    }

    @MethodSource("provideArgumentsForCalculatorTests")
    @ParameterizedTest
    public void shouldReturnCorrectResultOfDividing(int num1, int num2) throws com.skypro.calculator.exceptions.ZeroDividerException {
        assertEquals(num1 / num2, out.divide(num1, num2));
    }
}
