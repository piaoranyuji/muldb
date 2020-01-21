package com.test.svc.utils;

import com.test.svc.constants.RecSt;
import com.test.svc.dao.mgm.MenuMapper;
import com.test.svc.model.mgm.Menu;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @description 更新内存数据
 */
@Component
public class QueryCacheManageService {

    @Resource
    private MenuMapper menuMapper;

    private static QueryCacheManageService dbCacheMapper;

    @PostConstruct
    public void init() {

        dbCacheMapper = this;
        // 以下5行代码可以注释，也可以保留
        dbCacheMapper.menuMapper = this.menuMapper;
    }

    public List<Menu> listMenu() {
        Menu menu = new Menu();
        menu.setRecSt(RecSt._1.getCode());
        return dbCacheMapper.menuMapper.selectSelective(menu);
    }
}
