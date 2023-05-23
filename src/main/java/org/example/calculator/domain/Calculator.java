package org.example.calculator.domain;

import org.example.calculator.tobe.MultiplicationOperator;
import org.example.calculator.tobe.SubtractionOperator;
import org.example.calculator.tobe.AdditionOperator;
import org.example.calculator.tobe.DivisionOperator;
import org.example.calculator.tobe.ArithmeticOperator;

import java.util.List;

public class Calculator {
//    public static int calcurate(int operate1, String operator, int operate2) { // 2개의 피연산자와 1개의 연산자필요
//        if ("+".equals(operator)) {
//            return operate1 + operate2;
//        } else if ("-".equals(operator)) {
//            return operate1 - operate2;
//        } else if ("*".equals(operator)) {
//            return operate1 * operate2;
//        } else if ("/".equals(operator)) {
//            return operate1 / operate2;
//        }
//        return 0;
//    }

    // 위 코드를 1차 Refactoring
//    public static int calculate(int operate1, String operator, int operate2) { // 2개의 피연산자와 1개의 연산자필요
//       return ArithmeticOperator.calculate(operate1,operator,operate2); //ArithmeticOperator에 다시 작업 위임(객체지향)
//    }

    // 위 코드를 2차 Refactoring
    // 각각의 구현체들을 상위 인터페이스인 NewArithmeticOperator로 받아서 처리
//    public static final List<NewArithmeticOperator> arithmeticOperators = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());
//    public static int calculate(int operand1, String operator, int operand2) {
//        return arithmeticOperators.stream()
//                .filter(arithmeticOperator -> arithmeticOperator.supports(operator)) // 입력받은 operator에 해당하는 구현체를 찾고
//                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))// 구현체에게 calculate 작업을 위임(해당값이 int라서 map으로 묶는다)
//                .findFirst()    // 첫번째 값을 받는다.
//                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));    // 연산자에 해당하는 해당 구현체가 없으면 예외처리
//    }

    // 위 코드를 3차 Refactoring
    // 양수로만 계산할 수 있다.(PositiveNumber) 조건 반영
    public static final List<ArithmeticOperator> arithmeticOperators = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());
    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return arithmeticOperators.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator)) // 입력받은 operator에 해당하는 구현체를 찾고
                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))// 구현체에게 calculate 작업을 위임(해당값이 int라서 map으로 묶는다)
                .findFirst()    // 첫번째 값을 받는다.
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));    // 연산자에 해당하는 해당 구현체가 없으면 예외처리
    }
}
