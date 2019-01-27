package com.lin.cms.admin.service;

import com.lin.cms.mbg.model.User;

/**
 * Created by zhouyajun on 2019/1/21
 */
public interface UserAdminService {

    /**
     * d登录功能
     * @param username
     * @param password
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    User getAdminByUsername(String username);
}
