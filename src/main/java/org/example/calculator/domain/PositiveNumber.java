package org.example.calculator.domain;

public class PositiveNumber {   //양수로만 계산할 수 있다.
    private final int value;

    public PositiveNumber(int value) {
        validate(value);
        this.value = value;
    }
    private void validate(int value) {
        if (isNegativeNumber(value)) {
            throw new IllegalArgumentException("0또는 음수를 전달할 수 없습니다.");
        }
    }
    private boolean isNegativeNumber(int value) {
        return value <= 0;
    }

    public int toInt() {    // value 값을 int로 바꿔주는 메소드
        return value;
    }
}
