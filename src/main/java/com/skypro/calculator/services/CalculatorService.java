package com.skypro.calculator.services;

import com.skypro.calculator.exceptions.ZeroDividerException;

public interface CalculatorService {

    int plus(int num1, int num2);

    int minus(int num1, int num2);

    double divide(int num1, int num2) throws ZeroDividerException;

    int multiply(int num1, int num2);
}
