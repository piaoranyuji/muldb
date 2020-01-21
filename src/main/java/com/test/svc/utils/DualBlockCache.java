package com.test.svc.utils;

import com.test.svc.ApplicationContextHelper;
import com.test.svc.constants.SvcConstant;
import com.test.svc.model.mgm.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DualBlockCache {

    /**
     * 内存块取用与刷新状态标志
     * 0：不可用（初态），下次定时任务刷新A cache块
     * 1：A cache块可用，下次定时任务刷新B cache块
     * 2：B cache块可用，下次定时任务刷新A cache块
     */
    public static int indicator = 0;

    // A cache缓存Object
    private static HashMap<String, Object> blockObjectA = new HashMap<>();

    // B cache缓存Object
    private static HashMap<String, Object> blockObjectB = new HashMap<>();

    // 缓存单线程
    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    // 缓存实例
    private static DualBlockCache cache = new DualBlockCache();

    private DualBlockCache() {
    }

    /**
     * 根据key值查找Object
     *
     * @param key 内存中的key值
     * @return 返回Object对象
     */
    public Object getObject(String key) {
        switch (indicator) {
            case 1:
                return blockObjectA.get(key);
            case 2:
                return blockObjectB.get(key);
            default:
                return null;
        }
    }

    public static DualBlockCache getInstance() {
        return cache;
    }

    public void start() {

        // 定时刷新内存数据
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                // 刷新内存参数
                flushData();

                // 根据reloadCache参数开关判断是否执行下次刷新任务，0：关闭，不刷新内存；1：开启，定时刷新内存,进程shutdown后不能恢复
                Environment environment = ApplicationContextHelper.applicationContext.getEnvironment();
                String reloadCache = PropertyUtil.getProperties(environment.getProperty("conf.file.path"), "reloadCache");
                if ("0".equals(reloadCache)) {
                    executorService.shutdown();
                }
            }
        }, 1L, 600L, TimeUnit.SECONDS); // 项目启动后1秒开始执行，此后10分钟刷新一次
    }

    /**
     * 刷新内存数据
     */
    public void flushData() {

        log.info(DateUtils.now() + " start to reload A-B Cache task ===");

        HashMap<String, Object> blockObject = (indicator == 1) ? blockObjectB : blockObjectA;

        blockObject.clear();

        QueryCacheManageService queryCacheManageService = new QueryCacheManageService();

        List<Menu> listMenu = queryCacheManageService.listMenu();
        for (Menu menu : listMenu) {
            blockObject.put(SvcConstant.MENUINFO + menu.getMenuId(), menu);
        }

        indicator = (indicator == 1) ? 2 : 1;
        log.info("blockObject size : " + blockObject.size());
        log.info("内存数据刷新完毕，indicator = {}", indicator);
    }
}
