package org.sunovivid.hellospringbootagain.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //EnableJpaAuditing 컨피규레이션을 Application에서 분리했다.
public class JpaConfig {
}
