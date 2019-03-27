package com.ppdai.qa.batme.contract.user.request;

import com.ppdai.qa.batme.core.enums.EncryptTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String login_name;
    private String login_pwd;
    @Builder.Default
    private Integer encrypt_type = EncryptTypeEnum.AES.getCode();
}
