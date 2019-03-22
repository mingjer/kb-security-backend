package com.ppdai.qa.batme.server.controller.chat;

import com.alibaba.fastjson.JSON;
import com.ppdai.qa.batme.contract.common.GenericResponse;
import com.ppdai.qa.batme.core.constants.ResponseConstants;
import com.ppdai.qa.batme.model.chat.entity.ChatInfo;
import com.ppdai.qa.batme.model.chat.search.ChatInfoSearch;
import com.ppdai.qa.batme.server.service.chat.IChatInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;
import java.util.Date;

@RestController
@Slf4j
public class ChatInfoController {
    @Resource
    private IChatInfoService chatInfoService;

    /**
     * 添加chatinfo
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/chatinfos")
    public GenericResponse add_chat(@RequestBody ChatInfo info) {
        GenericResponse response = new GenericResponse();
        try {
            info.setCreate_time(new Date());
            info.setEmployee_name("孔彬");
            Integer count = chatInfoService.insert(info);
            response.setData(count);
            response.setStatus(ResponseConstants.SUCCESS_CODE);
        } catch (Exception e) {
            response.setStatus(ResponseConstants.FAIL_CODE);
            log.error("添加chatinfo异常：" + e.getCause());
        }
        return response;
    }

    /**
     * 搜索chatinfo 列表
     *
     * @param searchKey
     * @param type
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/chatinfos/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse search_chat_list(@RequestParam String searchKey, @RequestParam(required = false) Integer type, @RequestParam Integer pageNum, @RequestParam(required = false) Boolean openSqlHack, @RequestParam(required = false) String employee_name) {
        GenericResponse response = new GenericResponse();
        try {
            ChatInfoSearch search = ChatInfoSearch.builder()
                    .pageNum(pageNum)
                    .employee_name(employee_name)
                    .searchKey(searchKey)
                    .type(type)
                    .build();
            List<ChatInfo> chatInfoList = new ArrayList<>();

            //根据安全开关判断 是否走不安全的代码逻辑
            if (openSqlHack != null && openSqlHack) {
                chatInfoList = chatInfoService.searchListNoSafe(search);
            } else {
                chatInfoList = chatInfoService.searchList(search);
            }

            Integer count = chatInfoService.searchCount(search);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("count", count);
            resultMap.put("dataList", chatInfoList);
            response.setStatus(ResponseConstants.SUCCESS_CODE);
            response.setData(resultMap);
        } catch (Exception e) {
            response.setStatus(ResponseConstants.FAIL_CODE);
            if (openSqlHack != null && openSqlHack) {
                response.setMessage(JSON.toJSONString(e.getCause()));
            }
            log.error("搜索chatinfo列表异常：" + e.getCause());
        }
        return response;

    }

}
