package me.jungeun.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // TODO
// 엔티티 객체가 생성이 되거나 변경 되었을 때 @EnableJpaAuditing
// 어노테이션으 활용하여 자동으로 값을 등록할 수 있다

@SpringBootApplication // TODO
// 스프링 부트의 자동 설정, 스프링 bean 읽기와 생성을 모두 자동으로 설정
// 쓴 위치부터 설정을 읽어가기 때문에 클래스는 항상 프로젝트의 최상단에 위치해야함
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
