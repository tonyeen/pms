package org.qianrenxi.core.schedule.quartz.config;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import javax.sql.DataSource;

import org.qianrenxi.core.schedule.quartz.DemoJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by david on 2015-01-20.
 * This a demo which is show you how to integrate quzrtz and springboot 
 */
//@Configuration
//@ConditionalOnProperty(name = "quartz.enabled")
@Deprecated
public class QuartzConfiguration2 {
	
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext)
    {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
    public JobDetail sampleJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();

        factoryBean.setJobClass(DemoJob.class);
        factoryBean.setDurability(true);
        factoryBean.setName("test-job");

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    public Trigger sampleJobTrigger() {

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(sampleJobDetail());
        factoryBean.setName("test-trigger");
        factoryBean.setCronExpression("0/10 * * * * ?");
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CronTrigger object = factoryBean.getObject();

        return object;
    }
	
	
	
	
	@Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(sampleJobTrigger());

        return factory;
    }
    
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    
}