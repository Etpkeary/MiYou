package com.miyou.miyou_backend.service;

import com.miyou.miyou_backend.model.vo.MiYouTableUserInfoVO;
import com.miyou.miyou_backend.once.importuser.TableListener;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Excel 服务
 *
 * @author 34285
 */
public interface ExcelService {


    /**
     * 上传Excel
     *
     * @param file 文件
     * @param head MiYouTableUserInfoVO
     * @param tableListener 表侦听器
     * @return
     */
    List<MiYouTableUserInfoVO> uploadExcel(MultipartFile file, Class head, TableListener tableListener);
}
