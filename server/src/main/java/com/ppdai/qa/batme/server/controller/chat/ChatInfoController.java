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
import java.util.List;

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
    @RequestMapping(value = "/chatinfos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
            List<ChatInfo> chatInfoList = chatInfoService.searchList(ChatInfoSearch.builder()
                    .pageNum(pageNum)
                    .searchKey(searchKey)
                    .type(type)
                    .build());
            response.setStatus(ResponseConstants.SUCCESS_CODE);
            response.setData(chatInfoList);
        } catch (Exception e) {
            response.setStatus(ResponseConstants.FAIL_CODE);
            log.error("搜索chatinfo列表异常：" + e.getCause());
        }
        return response;

    }
}
