package com.miyou.miyou_backend.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * MiYou表格用户信息
 *
 * @author LINGLAN
 */
@Data
public class MiYouTableUserInfoVO {

    /**
     * MiYou编号
     */
    @ExcelProperty("MiYou编号")
    private String MiYouCode;

    /**
     * 用户名称
     */
    @ExcelProperty("成员名称")
    private String userName;

}