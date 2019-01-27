package com.lin.cms.admin.controller;

import com.lin.cms.admin.dto.CommonResult;
import com.lin.cms.admin.dto.UserAdminLoginParam;
import com.lin.cms.admin.service.UserAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouyajun on 2019/1/21
 */

@Controller
@RequestMapping(value = "/cms/user")
public class UserAdminController {

    @Autowired
    private UserAdminService userAdminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody UserAdminLoginParam param, BindingResult result) {
        String token = userAdminService.login(param.getUsername(), param.getPassword());
        LOGGER.info("token: {}", token);
        if (token == null) {
            return new CommonResult().validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }

}
