package com.ppdai.qa.batme.server.service.blog.impl;

import com.ppdai.qa.batme.dao.mapper.blog.BlogInfoMapper;
import com.ppdai.qa.batme.model.blog.entity.BlogInfo;
import com.ppdai.qa.batme.server.service.blog.IBlogInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogInfoServiceImpl implements IBlogInfoService {
    @Resource
    private BlogInfoMapper mapper;

    @Override
    public Integer insert(BlogInfo info) {
        return mapper.insertSelective(info);
    }

    @Override
    public List<BlogInfo> selectList(BlogInfo info) {
        return mapper.selectList(info);
    }

    @Override
    public Integer update(BlogInfo info) {
        return mapper.updateByPrimaryKeySelective(info);
    }
}
