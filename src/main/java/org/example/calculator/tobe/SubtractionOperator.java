package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;
import org.example.calculator.tobe.ArithmeticOperator;

public class SubtractionOperator implements ArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "-".equals(operator);
    }

//    @Override
//    public int calculate(int operand1, int operand2) {
//        return operand1 - operand2;
//    }

    // 3차 Refactoring 양수로만 계산할 수 있다. 조건반영
    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt() - operand2.toInt();
    }
}
