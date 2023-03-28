package com.miyou.miyou_backend.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新个人信息请求
 *
 * @author LINGLAN
 *
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 用户名称
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 用户头像
     */
    @TableField(value = "userAvatar")
    private String userAvatar;

    /**
     * 用户性别  0 - 男 1 - 女
     */
    @TableField(value = "userGender")
    private Integer userGender;

    /**
     * 用户电话
     */
    @TableField(value = "userPhone")
    private String userPhone;

    /**
     * 用户邮箱
     */
    @TableField(value = "userEmail")
    private String userEmail;

//    /**
//     * 简介
//     */
//    private String userProfile;

    private static final long serialVersionUID = 1L;
}