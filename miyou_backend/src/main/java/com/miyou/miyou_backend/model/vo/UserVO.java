package com.miyou.miyou_backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视图
 *
 * @author LINGLAN
 * @TableName user
 */
@Data
public class UserVO implements Serializable {
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
     * 创建时间
     */
    @TableField(value = "userCreateTime")
    private Date userCreateTime;

    /**
     * 更新时间
     */
    @TableField(value = "userUpdateTime")
    private Date userUpdateTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}