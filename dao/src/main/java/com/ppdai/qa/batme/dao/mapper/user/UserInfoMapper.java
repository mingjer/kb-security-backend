package com.ppdai.qa.batme.dao.mapper.user;

import com.ppdai.qa.batme.model.user.entity.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    List<UserInfo> selectList(UserInfo info);
}
