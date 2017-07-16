package org.qianrenxi;

import org.qianrenxi.core.common.spring.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class PmsApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PmsApplication.class, args);
	}

	@Bean
	public SpringUtil springUtil() {
		return new SpringUtil();
	}
}
