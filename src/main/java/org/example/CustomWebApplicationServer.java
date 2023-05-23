package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {

    private final int port;

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public void start() throws IOException {
        // 해당 port로 server 띄우기(serverSocket을 생성)
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            // serverSocket이 client를 기다리게 한다.
            while ((clientSocket = serverSocket.accept()) != null) {
                // client와 연결
                logger.info("[CustomWebApplicationServer] client connected!");


                /**
                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 */
                // 클라이언트와 InputStream, OutputStream을 연결
                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
                    // InputStream을 한줄 한줄 읽기 위해 reader로 바꿔준다.
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

                    // HttpRequest를 만들고 BufferedReader를 전달
                    HttpRequest httpRequest = new HttpRequest(br);

                    // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {    // GET요청이면서 path가 /calculate일때만 queryStrings을 가져온다.
                        QueryStrings queryStrings = httpRequest.getQueryStrings();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate((new PositiveNumber(operand1)), operator, new PositiveNumber(operand2));
                        byte[] body = String.valueOf(result).getBytes();

                        HttpResponse response = new HttpResponse(dos);
                        response.response200Header("application/json", body.length);    //body는 content의 length
                        response.responseBody(body);
                    }
                }
            }
        }
    }
}
