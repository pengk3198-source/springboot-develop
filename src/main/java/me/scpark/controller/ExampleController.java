package me.scpark.controller;

import org.springframework.ui.Model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleaf(Model model){
        Person person=new Person();
        person.setName("pk");
        person.setAge(21);
        person.setHobbies(String.valueOf(List.of("sport","ready","game")));
        //System.out.println(person.getName());
        model.addAttribute("student",person);
        return "example";

    }
    @Getter
    @Setter
    class Person{
        private  String name;
        private  int age;
        private  String hobbies;

    }
}
