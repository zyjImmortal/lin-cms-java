package com.lin.cms.admin.service;

import com.lin.cms.admin.dto.UserAdminParam;
import com.lin.cms.admin.dto.UserInfoParam;
import com.lin.cms.mbg.model.User;

/**
 * Created by zhouyajun on 2019/1/21
 */
public interface UserService {

    /**
     * d登录功能
     * @param username
     * @param password
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    User getAdminByUsername(String username);

    /**
     * 注册功能
     *
     * @param userAdminParam
     * @return
     */
    User register(UserAdminParam userAdminParam);

    /**
     * 更新用户信息
     * @param userInfoParam
     * @return
     */
    User update(UserInfoParam userInfoParam);

    /**
     * 获取用户信息
     * @return
     */
    User getUserInfomation();
}
