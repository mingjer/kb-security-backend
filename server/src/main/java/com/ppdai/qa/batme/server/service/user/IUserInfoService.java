package com.ppdai.qa.batme.server.service.user;

import com.ppdai.qa.batme.model.user.entity.UserInfo;

import java.util.List;

public interface IUserInfoService {
    Integer insert(UserInfo userInfo);

    List<UserInfo> selectList(UserInfo userInfo);

    UserInfo findByName(String name);

    UserInfo get(Integer id);
}
