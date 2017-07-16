package org.qianrenxi.core.system.shiro.inteceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


public class InterceptorConfiguration extends WebMvcConfigurerAdapter{
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new ShiroTestInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/**");
    }
}
