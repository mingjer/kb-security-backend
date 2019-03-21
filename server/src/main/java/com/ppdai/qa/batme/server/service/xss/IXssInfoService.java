package com.ppdai.qa.batme.server.service.xss;

import com.ppdai.qa.batme.model.xss.entity.XssInfo;
import com.ppdai.qa.batme.model.xss.search.XssInfoSearch;

import java.util.List;

public interface IXssInfoService {
    Integer insert(XssInfo info);

    XssInfo get(Integer id);

    List<XssInfo> searchList(XssInfoSearch search);

    Integer searchCount(XssInfoSearch search);

    Integer update(XssInfo info);
}
