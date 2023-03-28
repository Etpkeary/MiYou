-- 创建库
create database if not exists miyou;

-- 切换库
use miyou;

-- 用户表
-- auto-generated definition
create table user
(
    userId         bigint auto_increment comment '用户id' primary key,
    MiYouCode      varchar(256)                           null comment 'MiYou编号',
    userName       varchar(256)                           null comment '用户名称',
    userAccount    varchar(256)                           not null comment '用户账号',
    userAvatar     varchar(1024)                          null comment '用户头像',
    userGender     tinyint                                null comment '用户性别  0 - 男 1 - 女',
    userRole       varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPhone      varchar(128)                           null comment '用户电话',
    userEmail      varchar(512)                           null comment '用户邮箱',
    userStatus     varchar(256) default 'NormalAccount'   not null comment '用户状态 Normal account - 正常 SealAccount - 封号',
    userProfile    text                                   null comment '用户简介',
    userPassword   varchar(512)                           not null comment '密码',
    userCreateTime datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    userUpdateTime datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    userIsDelete   tinyint      default 0                 not null comment '用户是否删除 0 - 未删除 1 - 删除(软删除)',
    constraint uni_userAccount
        unique (userAccount)
)
    comment '用户';


