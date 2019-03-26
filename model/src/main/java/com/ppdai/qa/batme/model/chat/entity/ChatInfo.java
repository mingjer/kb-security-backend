package com.ppdai.qa.batme.model.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatInfo {
    private Integer id;

    private String title;

    private String content;

    private String employee_name;

    private Date create_time;

    private Integer praise_count;
}
