package org.qianrenxi.core.system.config;

import java.util.List;

import org.qianrenxi.core.system.annotation.CurrentUserMethodArgumentResolver;
import org.qianrenxi.core.system.enity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableJpaAuditing
public class SystemConfig extends WebMvcConfigurerAdapter {

	@Bean
	public AuditorAware<User> auditorProvider(){
		return new AuditorAwareImpl();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		CurrentUserMethodArgumentResolver ur = new CurrentUserMethodArgumentResolver();
		argumentResolvers.add(ur);
	}
}
