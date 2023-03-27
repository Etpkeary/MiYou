package com.miyou.miyou_backend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.miyou.miyou_backend.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务接口
 *
 * @author LINGLAN
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2023-03-27 09:43:48
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request 请求体
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户登出
     *
     * @param request 请求体
     * @return true
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 请求体
     * @return 返回当前用户的信息
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request 请求体
     * @return 用户权限
     */
    boolean isAdmin(HttpServletRequest request);


}
