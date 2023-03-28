package com.miyou.miyou_backend.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新请求
 *
 * @author LINGLAN
 */
@Data
public class UserUpdateRequest implements Serializable {

    /**
     * 用户昵称
     */
    private Long userId;

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
     * 用户权限: user, admin
     */
    private String userRole;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户是否删除 0 - 未删除 1 - 删除(软删除)
     */
    private Integer userIsDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}