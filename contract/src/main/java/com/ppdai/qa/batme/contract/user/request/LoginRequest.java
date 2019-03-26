package com.ppdai.qa.batme.contract.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String login_name;
    private String login_pwd;
    private Integer cookie_type;
}