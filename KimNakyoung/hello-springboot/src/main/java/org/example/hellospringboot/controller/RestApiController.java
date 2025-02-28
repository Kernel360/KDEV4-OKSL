package org.example.hellospringboot.controller;

import org.example.hellospringboot.model.BookQueryParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path = "/hello")
    public String hello(){
        var html = "<html> <body> <h1> Hello Spring Boot </h1> </body> </html>";
        return html;
    }
    // 경로 변수와 메서드 변수와 경로 변수 매핑해줘야함
    // is-man 주소에 대문자 안써서
    // {isMan}은 boolean의 결과값이라 T/F로 와서 노상관
    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            // URL 경로의 일부를 변수로 받아오기 위해 사용
            @PathVariable(name = "message") String msg,
            @PathVariable int age, // Integer (null 가능) int (null 불가능)
            @PathVariable boolean isMan
    ){
        System.out.println("echo message : "+msg);
        System.out.println("echo age : "+age);
        System.out.println("echo isMan : "+isMan);


        // String 타입의 변수 외에 다른 타입 받아보기


        // boolean, integer

        return msg.toUpperCase(); // TODO 대문자로 변환해서 RETURN => toUpperCase
    }

    //쿼리 파라미터
    // http://localhost:8080/api/book?category=IT&issuedYear=2023&issued-month=01&issued_day=31
    @GetMapping(path = "/book")
    public void queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam(name = "issued-month") String issuedMonth, // java에서 issuedMonth 라서 네임에 issued-month
            @RequestParam(name = "issued_day") String issuedDay
    ){
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issuedDay);
    }
    // http://localhost:8080/api/book2?category=IT&issuedYear=2023&issuedMonth=01&issuedDay=31
    @GetMapping(path = "/book2")
    public void queryParamDto(
            BookQueryParam bookQueryParam
    ){
        System.out.println(bookQueryParam);
    }
    // 한번에 객체로 받을 때는 name 안에 있는거 맴핑 안됨

    // TODO Parameter 2가지 받습니다. int 형태로 받아서 두 수의 덧셈, 곱셈

    //http://localhost:8080/api/calculate?num1=5&num2=3

    @GetMapping(path="/calculate")
    public String calculate(
            @RequestParam int num1,
            @RequestParam int num2

    ){
        int sum = num1 + num2;
        int multiply = num1 * num2;

        return "덧셈 : " + sum + ", 곱셈 :" + multiply;
    }


    // TODO String 타입 boolean 타입도 받아보기

    //http://localhost:8080/api/two-type?s=apple&b=true

    @GetMapping(path = "/two-type")
    public String twoType(
            @RequestParam String s,
            @RequestParam boolean b
    ){
        String fruit = s;
        Boolean f = b;
        return   "과일 : " + fruit + ", 유무 :" + f;
    }










}
