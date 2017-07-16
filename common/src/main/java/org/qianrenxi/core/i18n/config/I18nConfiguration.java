package org.qianrenxi.core.i18n.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * 国际化相关配置
 * 
 * @author jiawei
 *
 */
@Configuration
public class I18nConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * 使用cookie区域解析器
	 * 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver slr = new CookieLocaleResolver();
		// 设置默认区域,
		slr.setDefaultLocale(Locale.CHINA);
		slr.setCookieMaxAge(0);// 设置cookie有效期.
		return slr;
	}
	/**
	 * 使用session区域解析器，缺点是每次登录都会重置为默认语言，可以在登陆成功后根据用户配置设置Locale
	 * @see {@link org.qianrenxi.core.i18n.controller.I18nTestController#changeSessionLanauage()}
	 * @return
	 */
	/*@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		// 设置默认区域,
		slr.setDefaultLocale(Locale.CHINA);
		return slr;
	}*/

	/**
	 * 使用参数修改用户的区域,默认为：locale,中文参数值为（zh_CN）,英文（en_US）
	 * 
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		// 设置请求地址的参数,默认为：locale
		lci.setParamName(LocaleChangeInterceptor.DEFAULT_PARAM_NAME);
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
