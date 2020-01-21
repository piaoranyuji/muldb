package com.test.svc.dao.onl;

import com.test.svc.model.onl.Sp;
import com.test.svc.model.onl.SpKey;

public interface SpMapper {
    int deleteByPrimaryKey(SpKey key);

    int insert(Sp record);

    int insertSelective(Sp record);

    Sp selectByPrimaryKey(SpKey key);

    int updateByPrimaryKeySelective(Sp record);

    int updateByPrimaryKey(Sp record);
}