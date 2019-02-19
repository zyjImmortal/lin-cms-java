package com.lin.cms.admin.service.impl;

import com.lin.cms.admin.dto.UserAdminParam;
import com.lin.cms.admin.dto.UserInfoParam;
import com.lin.cms.admin.service.UserAdminService;
import com.lin.cms.admin.util.JwtTokenUtil;
import com.lin.cms.mbg.mapper.UserMapper;
import com.lin.cms.mbg.model.User;
import com.lin.cms.mbg.model.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouyajun on 2019/1/21
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);

    @Override
    public String login(String username, String password) {
        String token = null;
        System.out.println(password);

        try {
            // 根据userdetailes用户信息构建安全对象
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    password);
//            通过authenticationManager中提供的Prodiver对安全对象进行认证
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            System.out.println(authentication.toString());
//            将认证对象添加到安全管理的上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = jwtTokenUtil.generateToken(userDetails.getUsername());

        } catch (AuthenticationException e) {
            e.printStackTrace();
            LOGGER.info("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public User getAdminByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andNicknameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User register(UserAdminParam userAdminParam) {
        User user = new User();
        BeanUtils.copyProperties(userAdminParam, user);
        user.setCreateTime(new Date());
        user.setActive(1);
        user.setRole(2);
        UserExample example = new UserExample();
        example.createCriteria().andNicknameEqualTo(user.getNickname());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return null;
        } ;
        String md5Password = passwordEncoder.encode(user.getPassword());
        user.setPassword(md5Password);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(UserInfoParam userInfoParam){
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(userInfoParam.getEmail());
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()){
            throw new UsernameNotFoundException("用户不存在=>email:" + userInfoParam.getEmail());
        }
        User user = users.get(0);
        user.setEmail(userInfoParam.getEmail());
        user.setUpdateTime(new Date());
        userMapper.updateByExample(user, example);
        return user;
    }

    @Override
    public User getUserInfomation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getCredentials();
        User user = this.getAdminByUsername(username);
        return user;
    }
}
