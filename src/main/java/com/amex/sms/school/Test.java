package com.amex.sms.school;

import com.amex.sms.school.student.entity.Student;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

class Per{

}
class Stu extends Per implements Runnable{
    @Override
    public void run() {
        System.out.println("Running ThreadTest1");
    }
}

class ThreadTest2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Running ThreadTest2");
    }
}

public class Test {

    public static void main(String a[]){

    }

    public static void print(Optional<Student> s){
        if(s.isPresent())
        System.out.println(s.get().getFname());


    }

    public static void main1(String a[]){
        RestTemplate restTemplate = new RestTemplate();
        /*restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }
        });*/
        String apiUrl = "http://localhost:8080/students/190";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "NO");
            Student student = new Student();
            HttpEntity entity = new HttpEntity(headers);
            // Send an HTTP GET request
            //ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

            // Process the response
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();
                System.out.println("Response: " + responseBody);
            } else {
                System.err.println("Unexpected HTTP status: " + responseEntity.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            // Handle HTTP client errors (4xx status codes)
            if (e.getStatusCode().is4xxClientError()) {
                System.err.println("Client error: " + e.getStatusCode() + " - " + e.getStatusText());
                System.err.println("Response Body: " + e.getResponseBodyAsString());
            } else {
                System.err.println("Unexpected HTTP status: " + e.getStatusCode());
            }
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static String findBlockColor(int x, int y) {
        if(isEven(x+y)){
            return "Black";
        }
        return "White";
    }

    private static boolean isEven(int x){
        return x%2 == 0;
    }

}
