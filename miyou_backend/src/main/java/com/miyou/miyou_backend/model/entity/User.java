package com.miyou.miyou_backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户
 * @author LINGLAN
 * @createDate 2023年3月27日09点48分
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "userId", type = IdType.AUTO)
    private Long userId;

    /**
     * MiYou编号
     */
    @TableField(value = "MiYouCode")
    private String miYouCode;

    /**
     * 用户名称
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 用户账号
     */
    @TableField(value = "userAccount")
    private String userAccount;

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
     * 用户角色：user / admin
     */
    @TableField(value = "userRole")
    private String userRole;

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

    /**
     * 用户状态 Normal account - 正常 SealAccount - 封号
     */
    @TableField(value = "userStatus")
    private String userStatus;

    /**
     * 密码
     */
    @TableField(value = "userPassword")
    private String userPassword;

    /**
     * 用户简介
     */
    @TableField(value = "userProfile")
    private String userProfile;

    /**
     * 创建时间
     */
    @TableField(value = "userCreateTime")
    private Date userCreateTime;

    /**
     * 更新时间
     */
    @TableField(value = "userUpdateTime")
    private Date userUpdateTime;

    /**
     * 用户是否删除 0 - 未删除 1 - 删除(软删除)
     */
    @TableField(value = "userIsDelete")
    private Integer userIsDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}