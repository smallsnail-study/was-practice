package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public interface ArithmeticOperator {
    boolean supports(String operator);  //operator를 지원하는지에 대한 메소드

//    int calculate(int operand1, int operand2);

    // 3차 Refactoring 양수로만 계산할 수 있다. 조건반영
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
