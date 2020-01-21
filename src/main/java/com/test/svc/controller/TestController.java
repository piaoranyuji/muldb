package com.test.svc.controller;

import com.alibaba.fastjson.JSON;
import com.test.svc.model.mgm.Menu;
import com.test.svc.service.QueryService;
import com.test.svc.utils.DualBlockCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description 刷新内存数据后台入口
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    private QueryService queryService;

    // 手动刷新内存业务参数
    @RequestMapping("/flushData")
    public void flushData() {
        DualBlockCache.getInstance().flushData();
        log.info("手动刷新缓存完成");
    }

    // 手动调整内存刷新标志位为初始态
    @RequestMapping("/setFlag")
    public int setFlag(String indicator) {
        DualBlockCache.indicator = Integer.parseInt(indicator);
        return DualBlockCache.indicator;
    }

    // 获取内存标志位
    @RequestMapping("/getFlag")
    public int getFlag() {
        return DualBlockCache.indicator;
    }

    // 根据 menuId 获取菜单详情（内存查找）
    @RequestMapping("/getMenu")
    public Menu getMenu(String menuId) {

        log.info("内存查询菜单详情，收到菜单主键:[{}]", menuId);
        Menu menu = queryService.getMenu(menuId);
        log.info("内存查询菜单详情，应答数据:[{}]", JSON.toJSONString(menu));
        return menu;
    }

    // 根据 menuId 获取菜单详情（数据库查找）
    @RequestMapping("/getMenu1")
    public Menu getMenu1(String menuId) {

        log.info("数据库查询菜单详情，收到菜单主键:[{}]", menuId);
        Menu menu = queryService.getMenu1(menuId);
        log.info("数据库查询菜单详情，应答数据:[{}]", JSON.toJSONString(menu));
        return menu;
    }
}
