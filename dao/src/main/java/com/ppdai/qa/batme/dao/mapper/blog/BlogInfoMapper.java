package com.ppdai.qa.batme.dao.mapper.blog;

import com.ppdai.qa.batme.model.blog.entity.BlogInfo;

import java.util.List;

public interface BlogInfoMapper {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(BlogInfo record);

    BlogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogInfo record);

    List<BlogInfo> selectList(BlogInfo info);
}
