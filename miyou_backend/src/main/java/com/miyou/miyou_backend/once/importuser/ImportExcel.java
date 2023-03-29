package com.miyou.miyou_backend.once.importuser;

import com.alibaba.excel.EasyExcel;
import com.miyou.miyou_backend.model.vo.MiYouTableUserInfoVO;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 导入 Excel
 *
 * @author LINGLAN
 */
public class ImportExcel {

    /**
     * 数据读取
     *
     */
    public static void DataReading(String fileName) {
        // 监听器读取
        readByListener(fileName);
        // 同步读取
        // synchronousRead(fileName);
    }
    
    /**
     * 监听器读取
     *
     * <p>1. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器
     * <p>2. 直接读即可
     *
     * @param fileName 文件名
     */
    @Test
    public static void readByListener(String fileName) {
        // 这里默认读取第一个sheet
        EasyExcel.read(fileName, MiYouTableUserInfoVO.class, new TableListener()).sheet().doRead();
    }

    /**
     * 同步读，不推荐使用，如果数据量大会把数据放到内存里面
     *
     * @param fileName 文件名
     */
    @Test
    public static void synchronousRead(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<MiYouTableUserInfoVO> list = EasyExcel.read(fileName).head(MiYouTableUserInfoVO.class).sheet().doReadSync();
        for (MiYouTableUserInfoVO miYouTableUserInfo : list) {
            System.out.println(miYouTableUserInfo);
        }
    }


}