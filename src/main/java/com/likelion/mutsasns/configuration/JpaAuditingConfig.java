package com.likelion.mutsasns.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// [V] audit config 작성

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
