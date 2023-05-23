package org.example;

import java.util.Objects;

// GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
public class RequestLine {
    private final String method;    // GET
    private final String urlPath;   // /calculate

    private QueryStrings queryStrings;     // operand1=11&operator=*&operand2=55

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryString);  // QueryStrings안에 해당하는 queryStrings을 넣어준다.
    }

    public RequestLine(String requestLine) {
        // GET      /calculate ? operand1=11&operator=*&operand2=55    HTTP/1.1
        // tokens[0]  tokens[1]                                      tokens[2]
        //          urlPathTokens[0]    urlPathTokens[1]
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        // urlPath를 ? 기준으로 분리
        String[] urlPathTokens = tokens[1].split("\\?");

        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String requestPath) {
        return urlPath.equals(requestPath);
    }

    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }

    // 객체과 객체간 비교를 위해 equals() and hashCode()필요

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }
}
