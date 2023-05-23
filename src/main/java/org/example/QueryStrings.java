package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {
    private List<QueryString> queryStrings = new ArrayList<>();

    // operand1=11  operator=*  operand2=55
    public QueryStrings(String queryStringLine) {
        String[] queryStingTokens = queryStringLine.split("&"); // &연산자로 나누고
        Arrays.stream(queryStingTokens)
                .forEach(queryString -> {
                    String[] values = queryString.split("=");   // 나눈 객체를 다시한번 = 연산자로 나눈다.
                    if (values.length != 2) {
                        throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열입니다.");
                    }
                    queryStrings.add(new QueryString(values[0], values[1]));    // List에 초기화해준다.

                });
    }

    public String getValue(String key) {
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exists(key)) // key가 존재하면,
                .map(QueryString::getValue) // value를 map
                .findFirst()
                .orElse(null);
    }
}

