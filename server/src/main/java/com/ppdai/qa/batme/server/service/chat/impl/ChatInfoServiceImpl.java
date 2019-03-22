package com.ppdai.qa.batme.server.service.chat.impl;

import com.ppdai.qa.batme.dao.mapper.chat.ChatInfoMapper;
import com.ppdai.qa.batme.model.chat.entity.ChatInfo;
import com.ppdai.qa.batme.model.chat.search.ChatInfoSearch;
import com.ppdai.qa.batme.server.service.chat.IChatInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ChatInfoServiceImpl implements IChatInfoService {
    @Resource
    private ChatInfoMapper mapper;

    @Override
    public Integer insert(ChatInfo info) {
        return mapper.insertSelective(info);
    }

    @Override
    public List<ChatInfo> searchList(ChatInfoSearch search) {
        return mapper.searchList(search);
    }

    @Override
    public List<ChatInfo> searchListNoSafe(ChatInfoSearch search) {
        return mapper.searchListNoSafe(search);
    }

    @Override
    public Integer searchCount(ChatInfoSearch search) {
        return mapper.searchCount(search);
    }
}
