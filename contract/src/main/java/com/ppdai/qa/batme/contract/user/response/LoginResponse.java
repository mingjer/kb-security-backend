package com.ppdai.qa.batme.contract.user.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class LoginResponse {
    private String login_name;
    private Serializable token;
}
