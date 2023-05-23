package org.example.calculator.domain;

import java.util.Arrays;

public enum EnumArithmeticOperator {
    ADDITION("+"){
        @Override
        public int arithmeticCalculate(int operation1, int operation2) {
            return operation1 + operation2;
        }
    },SUBTRACTION("-"){
        @Override
        public int arithmeticCalculate(int operation1, int operation2) {
            return operation1 - operation2;
        }
    },MULTIPLICATION("*"){
        @Override
        public int arithmeticCalculate(int operation1, int operation2) {
            return operation1 * operation2;
        }
    },DIVISION("/"){
        @Override
        public int arithmeticCalculate(int operation1, int operation2) {
            return operation1 / operation2;
        }
    };

    private final String operator;

    EnumArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public abstract int arithmeticCalculate(final int operation1,final int operation2);

    // 외부에 노출되는 public interface
    public static int calculate(int operate1, String operator, int operate2) {
        EnumArithmeticOperator arithmeticOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));

        return arithmeticOperator.arithmeticCalculate(operate1,operate2);
    }
}
