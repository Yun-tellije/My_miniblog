package me.jungeun.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller // TODO
public class ExampleController {

    @GetMapping("/thymeleaf/example") // TODO
    public String thymeleafExample(Model model) {
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }

    @Setter
    // Lombok에서 가장 많이 사용되는 어노테이션, 자동으로 setPerson() 메소드 생성
    @Getter
    // Lombok에서 가장 많이 사용되는 어노테이션, 자동으로 getPerson() 메소드 생성
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}