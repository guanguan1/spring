package com.guan.spring.schedul;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Calendar;

/**
 * 测试
 *
 * @Author : guantenghua
 * @create 2020/6/3 11:20
 */
public class TestJob implements InitializingBean {

    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;

    public void getDefaultSchedulingConfigurer(DefaultSchedulingConfigurer configurer) {
        this.defaultSchedulingConfigurer = configurer;
    }

    public void afterPropertiesSet() {
        new Thread() {
            public void run() {
                try {
                    while (defaultSchedulingConfigurer.inited()) {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务调度初始化完成，添加任务");
                defaultSchedulingConfigurer.addTriggerTask("task", new TriggerTask(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("run job..." + Calendar.getInstance().get(Calendar.SECOND));
                    }
                }, new CronTrigger("0/5 * * * * ? ")));
            }
        }.start();
        new Thread() {
            public void run() {

                try {
                    Thread.sleep(30000);
                } catch (Exception e) {
                }
                System.out.println("重置任务............");
                defaultSchedulingConfigurer.restTriggerTask("task", new TriggerTask(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("run job..." + Calendar.getInstance().get(Calendar.SECOND));

                    }
                }, new CronTrigger("0/10 * * * * ? ")));
            }

            ;
        }.start();
    }
}
