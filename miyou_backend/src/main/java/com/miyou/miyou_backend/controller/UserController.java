package com.miyou.miyou_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.miyou.miyou_backend.common.BaseResponse;
import com.miyou.miyou_backend.common.DeleteRequest;
import com.miyou.miyou_backend.common.ErrorCode;
import com.miyou.miyou_backend.common.ResultUtils;
import com.miyou.miyou_backend.exception.BusinessException;
import com.miyou.miyou_backend.model.dto.user.*;
import com.miyou.miyou_backend.model.entity.User;
import com.miyou.miyou_backend.model.vo.UserVO;
import com.miyou.miyou_backend.service.UserService;
import com.miyou.miyou_backend.utlis.ThrowUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 * @author LINGLAN
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // region 登录相关

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求体
     * @return 用户注册结果
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求体
     * @param request 请求体
     * @return 用户
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户登出
     *
     * @param request 请求体
     * @return 用户登出的结果
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    // endregion

    // region 用户更新，查看个人信息

    /**
     * 获取当前登录用户的个人信息
     *
     * @param request 请求体
     * @return 用户脱敏后的信息
     */
    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    /**
     * 更新当前登录用户的个人信息
     *
     * @param userUpdateMyRequest 用户更新我的请求
     * @param request 请求体
     * @return true
     */
    @PostMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest, HttpServletRequest request) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        user.setUserId(loginUser.getUserId());
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    // endregion

    // region 搜索相关

    // endregion

    // region 用户推荐

    // endregion

    // region 增删改查

    /**
     * 创建用户 （仅管理员）
     *
     * @author LINGLAN
     * @param userAddRequest 用户添加请求体
     * @param request 请求体
     * @return 添加用户的id
     */
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest, HttpServletRequest request) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        boolean createuser = userService.save(user);
        if (!createuser) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(user.getUserId());
    }

    /**
     * 获取 id 删除用户（仅管理员）
     *
     * @author LINGLAN
     * @param deleteRequest 删除用户请求体
     * @param request 请求体
     * @return 删除用户的id
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> getDeleteUserById(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getUserId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean deleteuserid = userService.removeById(deleteRequest.getUserId());
        return ResultUtils.success(deleteuserid);
    }

    /**
     * 获取 id 更新用户 （仅管理员）
     *
     * @author LINGLAN
     * @param userUpdateRequest 更新用户请求体
     * @param request 请求体
     * @return 更新用户的id
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> getUpdateUser(@RequestBody UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        if (userUpdateRequest == null || userUpdateRequest.getUserId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean updateuserid = userService.updateById(user);
        return ResultUtils.success(updateuserid);
    }

    /**
     * 根据 id 获取用户（仅管理员）
     *
     * @author LINGLAN
     * @param id 用户的id
     * @param request 请求体
     * @return 脱敏的用户
     */
    @PostMapping("/get")
    public BaseResponse<UserVO> getUserById(int id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    /**
     * 获取用户列表 （仅管理员）
     *
     * @author LINGLAN
     * @param userQueryRequest 用户查询请求体
     * @param request 请求体
     * @return 用户信息脱敏的列表
     */
    @GetMapping("/list")
    public BaseResponse<List<UserVO>> listUser(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
        List<User> userList = userService.list(queryWrapper);
        List<UserVO> userVOList = userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(userVOList);
    }

    /**
     * 分页获取用户列表（仅管理员）
     *
     * @author LINGLAN
     * @param userQueryRequest 用户查询请求体
     * @param request 请求体
     * @return 用户脱敏的列表页数
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<UserVO>> listUserByPage(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        long current = 1;
        long size = 10;
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
            current = userQueryRequest.getCurrent();
            size = userQueryRequest.getPageSize();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
        Page<User> userPage = userService.page(new Page<>(current, size), queryWrapper);
        Page<UserVO> uservopage = new PageDTO<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        List<UserVO> uservolist = userPage.getRecords().stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        uservopage.setRecords(uservolist);
        return ResultUtils.success(uservopage);
    }

    // endregion
}
