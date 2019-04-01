package com.ppdai.qa.batme.server.service.blog;

import com.ppdai.qa.batme.model.blog.entity.BlogInfo;

import java.util.List;

public interface IBlogInfoService {
    Integer insert(BlogInfo info);

    List<BlogInfo> selectList(BlogInfo info);

    Integer update(BlogInfo info);

}
