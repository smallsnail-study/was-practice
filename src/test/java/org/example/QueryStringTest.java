package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// queryString을 key, value로 분리하기 위해 만든 클래스
public class QueryStringTest {

    // operand1 = 11
    // List = QueryString 쿼리스트링이 여러개인 경우
    @Test
    void createTest() {
        QueryString queryString = new QueryString("operand1", "11");

        assertThat(queryString).isNotNull();
    }
}
