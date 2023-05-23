package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final RequestLine requestLine;
//    계산기에서는 Header와 Body가 필요없기 떄문에 구현하지 않음.
//    private final HttpHeaders httpHeaders;
//    private final Body body;

    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine());  // 프로토콜의 1번째 라인이 Request이기 때문에 첫번째 라인을 넣으면 RequestLine 겍체가 세팅된다.

    }

    public boolean isGetRequest() {
        return requestLine.isGetRequest();  // HTTPRequest는 GET요청여부를 알 수 없으므로 requestLine에 다시한번 물어본다.(requestLine에 메소드값이 있으므로)
    }

    public boolean matchPath(String requestPath) {
        return requestLine.matchPath(requestPath);
    }

    public QueryStrings getQueryStrings() {
        return requestLine.getQueryStrings();
    }
}
