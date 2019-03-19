package com.ppdai.qa.batme.model.chat.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatInfoSearch {
    private String searchKey;
    private Integer pageSize = 10;
    private Integer pageNum = 0;
    private Integer startNum;
    private Integer type;

    public Integer getStartNum() {
        if (pageNum > 0) {
            return pageSize * (pageNum - 1);
        } else {
            return 0;
        }
    }
}
