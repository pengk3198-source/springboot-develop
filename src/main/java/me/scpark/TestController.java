package me.scpark;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hi")
    public String hi() {
        return "hello? 'http://localhost:8080/hi' request";
    }
    @GetMapping("/test")
    public String test() {
        return "hello? 'http://localhost:8080/test' request";
    }
}