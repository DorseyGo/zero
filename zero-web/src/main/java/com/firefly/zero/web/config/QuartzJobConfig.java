/**
 * File: QuartzJobConfig
 * Author: DorSey Q F TANG
 * Created: 2020/2/5 15:36
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.config;

import com.firefly.zero.web.job.GetAccessTokenServiceJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzJobConfig {
    @Bean
    public JobDetail getAccessTokenJobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(GetAccessTokenServiceJob.class)
                .withIdentity("get_access_token").storeDurably().build();

        return jobDetail;
    }

    @Bean
    public Trigger getAccessTokenTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("get_access_token_trigger").startNow()
                .forJob(getAccessTokenJobDetail()).withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/1 * * ? "))
                .build();

        return trigger;
    }
}
