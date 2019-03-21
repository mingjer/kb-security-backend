package com.ppdai.qa.batme.server.service.xss.impl;

import com.ppdai.qa.batme.dao.mapper.xss.XssInfoMapper;
import com.ppdai.qa.batme.model.xss.entity.XssInfo;
import com.ppdai.qa.batme.model.xss.search.XssInfoSearch;
import com.ppdai.qa.batme.server.service.xss.IXssInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XssInfoServiceImpl implements IXssInfoService {
    @Resource
    private XssInfoMapper mapper;

    @Override
    public Integer insert(XssInfo info) {
        return mapper.insertSelective(info);
    }

    @Override
    public XssInfo get(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<XssInfo> searchList(XssInfoSearch search) {
        return mapper.searchList(search);
    }

    @Override
    public Integer searchCount(XssInfoSearch search) {
        return mapper.searchCount(search);
    }

    @Override
    public Integer update(XssInfo info) {
        return mapper.updateByPrimaryKeySelective(info);
    }
}
