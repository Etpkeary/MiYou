package com.miyou.miyou_backend.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.miyou.miyou_backend.model.vo.MiYouTableUserInfoVO;
import com.miyou.miyou_backend.once.importuser.TableListener;
import com.miyou.miyou_backend.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * Excel 服务接口
 *
 * @author LINGLAN
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    /**
     * 读取上传的excel
     *
     * @param file
     * @param head
     * @param tableListener
     * @return
     */
    @Override
    public List<MiYouTableUserInfoVO> uploadExcel(MultipartFile file, Class head, TableListener tableListener) {
        try {
            //1.获取工作簿
            ExcelReaderBuilder readBook = EasyExcel.read(file.getInputStream(), head, tableListener);
            //2.获取sheet
            ExcelReaderSheetBuilder sheet = readBook.sheet();
            //3.获取Excel中的数据
            List<MiYouTableUserInfoVO> students = sheet.doReadSync();
            //4.返回数据
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
