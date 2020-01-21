package com.test.svc.service;

import com.test.svc.constants.SvcConstant;
import com.test.svc.dao.mgm.MenuMapper;
import com.test.svc.model.mgm.Menu;
import com.test.svc.utils.DualBlockCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @description 综合查询类
 * @date 2020/1/20 15:30
 */
@Service
@Slf4j
public class QueryService {

    @Resource
    private MenuMapper menuMapper;

    public Menu getMenu(String menuId) {
        return (Menu) DualBlockCache.getInstance().getObject(SvcConstant.MENUINFO + menuId);
    }

    public Menu getMenu1(String menuId) {
        try {
            return StringUtils.isEmpty(menuId) ? null : menuMapper.selectByPrimaryKey(Integer.parseInt(menuId));
        } catch (Exception e) {
            log.error("数据库查询菜单出现异常", e);
            return null;
        }
    }
}
