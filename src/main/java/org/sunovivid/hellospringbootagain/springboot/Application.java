package org.sunovivid.hellospringbootagain.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing //JPA Auditing 어노테이션을 모두 활성화
// 테스트에서 WebMvcTest에서는 @Entity클래스가 없으므로 에러발생. 기능을 분리하자
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
