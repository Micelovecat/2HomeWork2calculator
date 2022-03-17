package com.skypro.calculator.rest;

import com.skypro.calculator.exceptions.ZeroDividerException;
import com.skypro.calculator.services.CalculatorService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

        private final CalculatorService calculatorService;

        public CalculatorController(CalculatorService calculatorService) {
            this.calculatorService = calculatorService;
        }

        @GetMapping()
        public String answerHello() {
            return "Добро пожаловать в калькулятор";
        }

        @GetMapping(path = "/plus")
        public String plus(@RequestParam(name = "num1", required = false) Integer num1,
                           @RequestParam(name = "num2", required = false) Integer num2){
            if (num1 == null || num2 == null){
                return "Пожалуйста добавьте оба параметра";
            }
            return num1 + "+" + num2 + "=" + calculatorService.plus(num1, num2);
        }
        @GetMapping(path = "/minus")
        public String minus(@RequestParam(name = "num1", required = false) Integer num1,
                            @RequestParam(name = "num2", required = false) Integer num2){
            if (num1 == null || num2 == null){
                return "Пожалуйста добавьте оба параметра";
            }
            return num1 + "-" + num2 + "=" + calculatorService.minus(num1, num2);
        }
        @GetMapping(path = "/divide")
        public String divide(@RequestParam(name = "num1", required = false) Integer num1,
                             @RequestParam(name = "num2", required = false) Integer num2) throws ZeroDividerException {
            if (num1 == null || num2 == null){
                return "Пожалуйста добавьте оба параметра";
            }
            if (num2 == 0){
                return "Ошибка - на 0 не делится";
            }
            return num1 + "+" + num2 + "=" + calculatorService.divide(num1, num2);
        }
        @GetMapping(path = "/multiply")
        public String multiply(@RequestParam(name = "num1", required = false) Integer num1,
                               @RequestParam(name = "num2", required = false) Integer num2){
            if (num1 == null || num2 == null){
                return "Пожалуйста добавьте оба параметра";
            }
            return num1 + "+" + num2 + "=" + calculatorService.multiply(num1, num2);
        }
}
