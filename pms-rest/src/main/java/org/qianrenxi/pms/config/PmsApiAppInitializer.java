package org.qianrenxi.pms.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class PmsApiAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { PmsApiAppInitializer.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { PmsApiAppInitializer.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/*" };
	}

}
