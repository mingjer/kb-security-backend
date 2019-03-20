package com.ppdai.qa.batme.server.service.chat;

import com.ppdai.qa.batme.model.chat.entity.ChatInfo;
import com.ppdai.qa.batme.model.chat.search.ChatInfoSearch;

import java.util.List;

public interface IChatInfoService {
    Integer insert(ChatInfo info);

    List<ChatInfo> searchList(ChatInfoSearch search);

    Integer searchCount(ChatInfoSearch search);
}
