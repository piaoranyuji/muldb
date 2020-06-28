package com.test.svc;

import com.test.svc.utils.DualBlockCache;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class SvcListeners implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent e) {

        // 启动A B Cache 内存块更新内存常用业务参数
//        DualBlockCache.getInstance().start();
    }
}
