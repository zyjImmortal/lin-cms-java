package com.lin.cms.admin.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by zhouyajun on 2019/1/21
 */

@Getter
@Setter
public class UserAdminParam {
    @NotEmpty(message = "用户名不能为空")
    private String nickname;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不合法")
    private String email;
}
