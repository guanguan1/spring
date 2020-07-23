package com.guan.spring.eventasync;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听者
 * @Author : guantenghua
 * @create 2020/5/9 9:32
 */
@Component  // ApplicationListener接口，并指定监听的事件类型
public class DemoEventListener implements ApplicationListener<DemoEvent> {

    // 事件监听方法
    @Async // 开启异步
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) { // 对消息进行接受处理
        String msg = demoEvent.getMsg();
        System.out.println("DemoEventListener,监听到了 DemoEvent 发布的消息:" + msg);
        System.out.println(System.currentTimeMillis());
        if(msg.equals("测试消息监听")){
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
