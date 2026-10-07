package me.scpark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MenberService memberService;

    @GetMapping("/member")
    public List<Menber> getAllMembers() {
        return memberService.getAllMenbers();
    }
    @GetMapping("/hello123")
    public String hello123() {
        return "OK";
    }
}