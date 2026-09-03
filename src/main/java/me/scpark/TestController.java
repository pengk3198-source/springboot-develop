package me.scpark;


@RestController
public class TestController {

    @GetMapping("/hi")
    public String hi() {
        return "hello? 'http://localhost:8080/hi' request";
    }
    @GetMapping("/test")
    public String test() {
    }
}