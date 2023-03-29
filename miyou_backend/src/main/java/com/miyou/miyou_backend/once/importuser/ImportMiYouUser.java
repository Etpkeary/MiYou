package com.miyou.miyou_backend.once.importuser;

import com.alibaba.excel.EasyExcel;
import com.miyou.miyou_backend.model.vo.MiYouTableUserInfoVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 导入MiYou用户到数据库
 *
 * @author LINGLAN
 */
public class ImportMiYouUser {

    public static void ImportMiYouUserInDatabase(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<MiYouTableUserInfoVO> userInfoList = EasyExcel.read(fileName).head(MiYouTableUserInfoVO.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList.size());
        Map<String, List<MiYouTableUserInfoVO>> listMap =
                userInfoList.stream()
                        .filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUserName()))
                        .collect(Collectors.groupingBy(MiYouTableUserInfoVO::getUserName));
        for (Map.Entry<String, List<MiYouTableUserInfoVO>> stringListEntry : listMap.entrySet()) {
            if (stringListEntry.getValue().size() > 1) {
                System.out.println("username = " + stringListEntry.getKey());
                System.out.println("1");
            }
        }
        System.out.println("不重复昵称数 = " + listMap.keySet().size());
    }

}