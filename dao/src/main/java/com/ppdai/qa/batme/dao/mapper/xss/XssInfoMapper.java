package com.ppdai.qa.batme.dao.mapper.xss;

import com.ppdai.qa.batme.model.xss.entity.XssInfo;
import com.ppdai.qa.batme.model.xss.search.XssInfoSearch;

import java.util.List;

public interface XssInfoMapper {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(XssInfo record);

    XssInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XssInfo record);

    List<XssInfo> searchList(XssInfoSearch search);

    Integer searchCount(XssInfoSearch search);

}
