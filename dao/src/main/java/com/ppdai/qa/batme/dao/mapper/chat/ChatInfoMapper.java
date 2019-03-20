package com.ppdai.qa.batme.dao.mapper.chat;

import com.ppdai.qa.batme.model.chat.entity.ChatInfo;
import com.ppdai.qa.batme.model.chat.search.ChatInfoSearch;

import java.util.List;

public interface ChatInfoMapper {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(ChatInfo record);

    ChatInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatInfo record);


    List<ChatInfo> searchList(ChatInfoSearch search);

    Integer searchCount(ChatInfoSearch search);
}
