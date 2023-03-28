package com.miyou.miyou_backend.service;

import com.miyou.miyou_backend.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 用户服务测试
 *
 * @author LINGLAN
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @Test
    void testUserRegister() {
        String userAccount = "LOUTS";
        String userPassword = "12345";
        String checkPassword = "123456";
        try {
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试 添加用户
     */
    @Test
    void testAddUser() {
        User user = new User();
        user.setUserName("LOUTS01");
        user.setUserAccount("123");
        user.setUserAvatar("");
        user.setUserGender(0);
        user.setUserPassword("xxx");
        boolean result = userService.save(user);
        System.out.println(user.getUserId());
        Assertions.assertTrue(result);
    }

    /**
     * 测试 更新用户
     */
    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUserId(3L);
        user.setUserName("LOUTS02//*-");
        user.setUserAccount("123");
        user.setUserAvatar("");
        user.setUserGender(0);
        user.setUserPassword("xxx");
        boolean result = userService.updateById(user);
        System.out.println(user.getUserId());
        Assertions.assertTrue(result);
    }

    /**
     * 测试 删除用户
     */
    @Test
    void testDeleteUser() {
        boolean result = userService.removeById(4L);
        try {
            Assertions.assertTrue(result);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 测试 获取用户
     */
    @Test
    void testGetUser() {
        User user = userService.getById(3L);
        Assertions.assertNotNull(user);
        System.out.println(user);
    }


}