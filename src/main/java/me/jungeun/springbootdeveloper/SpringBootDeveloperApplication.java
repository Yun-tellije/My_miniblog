package me.jungeun.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // TODO
// 엔티티 객체가 생성이 되거나 변경 되었을 때 @EnableJpaAuditing
// 어노테이션으 활용하여 자동으로 값을 등록할 수 있다
@SpringBootApplication // TODO
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
