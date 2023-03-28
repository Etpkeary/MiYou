package com.miyou.miyou_backend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *
 * @author LINGLAN
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户性别
     */
    private Integer userGender;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

    /**
     * 用户密码
     */
    private String userPassword;

    private static final long serialVersionUID = 1L;
}