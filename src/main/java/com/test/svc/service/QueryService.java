package com.test.svc.service;

import com.test.svc.ApplicationContextHelper;
import com.test.svc.constants.SvcConstant;
import com.test.svc.dao.mgm.MenuMapper;
import com.test.svc.model.mgm.Menu;
import com.test.svc.utils.DualBlockCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 综合查询类
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

    /**
     * 游标查询全量列表
     *
     * @return 全部菜单列表
     */
    public List<Menu> listMenuByCursor() {

        long start = System.currentTimeMillis();
        List<Menu> menuList = new ArrayList<>();

        try {

            SqlSessionFactory mgmSqlSessionFactory = (SqlSessionFactory) ApplicationContextHelper.applicationContext.getBean("mgmSqlSessionFactory");
            SqlSession sqlSession = mgmSqlSessionFactory.openSession();

            Cursor<Menu> menus = sqlSession.selectCursor("com.test.svc.dao.mgm.MenuMapper.selectAll");
            Iterator iter = menus.iterator();

            while (iter.hasNext()) {
                Menu menu = (Menu) iter.next();
                menuList.add(menu);
            }
            menus.close();
            sqlSession.close();
        } catch (IOException e) {

            log.error("游标查询菜单列表出现异常", e);
        }

        long end = System.currentTimeMillis();
        log.info("游标查询菜单条数:[{}]，耗时:[{}]ms", menuList.size(), (end - start));
        return menuList;
    }

    /**
     * 查询全量列表
     *
     * @return 全部菜单列表
     */
    public List<Menu> listMenu() {

        long start = System.currentTimeMillis();
        List<Menu> menuList = new ArrayList<>();

        try {

            menuList = menuMapper.selectAll();
        } catch (Exception e) {

            log.error("查询菜单列表出现异常", e);
        }

        long end = System.currentTimeMillis();
        log.info("查询到菜单条数:[{}]，耗时:[{}]ms", menuList.size(), (end - start));
        return menuList;
    }
}
