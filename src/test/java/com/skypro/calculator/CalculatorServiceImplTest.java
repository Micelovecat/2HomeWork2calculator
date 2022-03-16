package com.skypro.calculator;

import com.skypro.calculator.exceptions.ZeroDividerException;
import com.skypro.calculator.services.impl.CalculatorServiceImpl;
import org.junit.jupiter.api.Test;

import static com.skypro.calculator.CalculatorServiceImplTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceImplTest {

//    private static final short THREE = 3;
//    private static final int ONE = 1;
//    private static final int TWO = 2;
    private CalculatorServiceImpl out = new CalculatorServiceImpl();

    @Test
    public void shouldReturn3WhenSum1And2(){
        assertEquals(THREE, out.plus(ONE, TWO));
    }

    @Test
    public void shouldReturn3WhenSum3And0(){
        assertEquals(THREE, out.plus(THREE, ZERO));
    }

    @Test
    public void shouldReturnMinus3WhenSum1AndMinus4(){
        assertEquals(MINUS_THREE, out.plus(ONE, MINUS_FOUR));
    }

    @Test
    public void shouldReturnMinus3WhenSumMinus1AndMinus2(){
        assertEquals(MINUS_THREE, out.plus(MINUS_ONE, MINUS_TWO));
    }

    @Test
    public void shouldReturnMinus1WhenSumMinus1And0(){
        assertEquals(MINUS_ONE, out.plus(MINUS_ONE, ZERO));
    }

    @Test
    public void shouldReturn0WhenSubtract2And2(){
        assertEquals(ZERO, out.minus(TWO, TWO));
    }

    @Test
    public void shouldReturn3WhenSubtract2AndMinus1(){
        assertEquals(THREE, out.minus(TWO, MINUS_ONE));
    }

    @Test
    public void shouldReturn3WhenMultiply1And3(){
        assertEquals(THREE, out.multiply(ONE, THREE));
    }

    @Test
    public void shouldReturnMinus3WhenMultiply1AndMinus3(){
        assertEquals(MINUS_THREE, out.multiply(ONE, MINUS_THREE));
    }

    @Test
    public void shouldReturn2WhenDivideMinus4AndMinus2() throws ZeroDividerException {
        assertEquals(TWO, out.divide(MINUS_FOUR, MINUS_TWO));
    }

    @Test
    public void shouldReturn1WhenDivide2And2() throws ZeroDividerException {
        assertEquals(ONE, out.divide(TWO, TWO));
    }

    @Test
    public void shouldZeroDividerExceptionWhenDivide1And0() {
        assertThrows(ZeroDividerException.class, () -> out.divide(ONE, ZERO));
    }

}
