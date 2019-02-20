package com.lin.cms.admin.service;

import com.lin.cms.admin.dto.UserInfoParam;
import com.lin.cms.mbg.model.User;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author zhouyajun
 * @date 2019/2/20
 */
public interface AdminService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> getAdminUsers();

    void changeUserPassword(Integer userId);

    void deleteUser(Integer userId);

    User updateUserinfo(UserInfoParam userInfoParam);

    void disableUser(Integer userId);

    void activeUser(Integer userId);

    void craeteGroup();
}
