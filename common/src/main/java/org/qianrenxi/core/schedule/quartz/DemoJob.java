package org.qianrenxi.core.schedule.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.stereotype.Component;

@QuartzJob(name="my-job",cronExp = "${schedule.demo.job.exp:0 */10 * * * ?}")
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
@Component
public class DemoJob implements Job{

    /*
     * <p>Title: execute</p>
     * <p>Description: </p>
     * @param context
     * @throws JobExecutionException
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("This is a schedule demo task, just ignore me!");
    }
}
