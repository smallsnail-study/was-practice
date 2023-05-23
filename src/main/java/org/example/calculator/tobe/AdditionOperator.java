package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public class AdditionOperator implements ArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "+".equals(operator);    //operator가 "+"와 같다면 boolean으로 전달
    }

//    @Override
//    public int calculate(int operand1, int operand2) {
//        return operand1 + operand2; // 덧셈 수행해서 return
//    }

    // 3차 Refactoring 양수로만 계산할 수 있다. 조건반영
    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt() + operand2.toInt(); // 덧셈 수행해서 return
    }
}
