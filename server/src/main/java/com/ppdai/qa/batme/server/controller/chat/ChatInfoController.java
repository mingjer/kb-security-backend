package com.ppdai.qa.batme.server.controller.chat;

import com.ppdai.qa.batme.contract.common.GenericResponse;
import com.ppdai.qa.batme.core.constants.ResponseConstants;
import com.ppdai.qa.batme.model.chat.entity.ChatInfo;
import com.ppdai.qa.batme.model.chat.search.ChatInfoSearch;
import com.ppdai.qa.batme.server.service.chat.IChatInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public GenericResponse search_chat_list(@RequestParam String searchKey, @RequestParam Integer type, @RequestParam Integer pageNum) {
        GenericResponse response = new GenericResponse();
        try {
            ChatInfoSearch search = ChatInfoSearch.builder()
                    .pageNum(pageNum)
                    .searchKey(searchKey)
                    .type(type)
                    .build();
            List<ChatInfo> chatInfoList = chatInfoService.searchList(search);
            Integer count = chatInfoService.searchCount(search);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("count", count);
            resultMap.put("dataList", chatInfoList);
            response.setStatus(ResponseConstants.SUCCESS_CODE);
            response.setData(resultMap);
        } catch (Exception e) {
            response.setStatus(ResponseConstants.FAIL_CODE);
            log.error("搜索chatinfo列表异常：" + e.getCause());
        }
        return response;

    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public GenericResponse test(@RequestParam Integer a) {
        GenericResponse response = new GenericResponse();
        try {

            response.setData(a);
        } catch (Exception e) {
            response.setStatus(ResponseConstants.FAIL_CODE);
            log.error("test：" + e.getCause());
        }
        return response;

    }
}
