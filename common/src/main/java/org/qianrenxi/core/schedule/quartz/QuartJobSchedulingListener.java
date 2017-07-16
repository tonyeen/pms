package org.qianrenxi.core.schedule.quartz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class QuartJobSchedulingListener
        implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(QuartJobSchedulingListener.class);
	@Autowired
	Environment environment ;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ApplicationContext applicationContext = event
                    .getApplicationContext();
            List<CronTrigger> cronTriggerBeans = this
                    .loadCronTriggerBeans(applicationContext);
            if (cronTriggerBeans.size() > 0) {
                SchedulerFactoryBean schedulerFactoryBean = applicationContext
                        .getBean(SchedulerFactoryBean.class, cronTriggerBeans);
                schedulerFactoryBean.setTriggers(cronTriggerBeans
                        .toArray(new Trigger[cronTriggerBeans.size()]));
                schedulerFactoryBean.afterPropertiesSet();
                schedulerFactoryBean.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CronTrigger> loadCronTriggerBeans(
            ApplicationContext applicationContext) {
        Map<String, Object> quartzJobBeans = applicationContext
                .getBeansWithAnnotation(QuartzJob.class);

        Set<String> beanNames = quartzJobBeans.keySet();

        List<CronTrigger> cronTriggerBeans = new ArrayList<CronTrigger>();

        for (String beanName : beanNames) {
            Object bean = quartzJobBeans.get(beanName);
            QuartzJob quartzJob = bean.getClass()
                    .getAnnotation(QuartzJob.class);

            logger.info("Scheduling a job for " + bean.getClass());
            JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();

            jobDetailFactoryBean.setJobClass(bean.getClass());
            jobDetailFactoryBean.setName(beanName);
            jobDetailFactoryBean.setDurability(true);
            jobDetailFactoryBean.afterPropertiesSet();

            CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();

            triggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
            triggerFactoryBean.setName(beanName + "_trigger");
            triggerFactoryBean.setStartDelay(30000);
            //logger.info(environment.getProperty("schedule.demo.job.exp"));
            
            String cronExp = getCronExp(environment, quartzJob.cronExp());
            triggerFactoryBean.setCronExpression(cronExp);
            triggerFactoryBean.setMisfireInstruction(
                    SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
            

            try {
                triggerFactoryBean.afterPropertiesSet();
                logger.info("cron expression is: " +
                        triggerFactoryBean.getObject().getCronExpression());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cronTriggerBeans.add(triggerFactoryBean.getObject());

        }

        return cronTriggerBeans;
    }

    private String getCronExp(Environment environment, String cronExp) {
		if(cronExp.startsWith("${")){
			cronExp = StringUtils.remove(cronExp, "${");
			cronExp = StringUtils.remove(cronExp, "}");
			return cronExp.contains(":")?environment.getProperty(cronExp.substring(0, cronExp.indexOf(":")),cronExp.substring(cronExp.indexOf(":")+1)):environment.getProperty(cronExp);
		} else if(cronExp.startsWith("#{")){
			cronExp = StringUtils.remove(cronExp, "#{");
			cronExp = StringUtils.remove(cronExp, "}");
			return cronExp.contains(":")?environment.getProperty(cronExp.substring(0, cronExp.indexOf(":")),cronExp.substring(cronExp.indexOf(":")+1)):environment.getProperty(cronExp);
		} else {
			return cronExp;
		}
	}

	public Trigger getCronTrigger(String cronExpression) {
        CronScheduleBuilder cronScheduleBuilder = null;
        Trigger cronTrigger = null;
        cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
        TriggerBuilder<Trigger> cronTtriggerBuilder = TriggerBuilder
                .newTrigger();
        cronTtriggerBuilder.withSchedule(cronScheduleBuilder);
        cronTrigger = cronTtriggerBuilder.build();

        return cronTrigger;
    }

}