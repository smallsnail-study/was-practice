package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);

    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        /**
         * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리
         * 하도록 한다.
         */
        // 현재 수행되는 스레드이름 log
        logger.info("[ClientRequestHandler] new client {} started.", Thread.currentThread().getName());

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
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
