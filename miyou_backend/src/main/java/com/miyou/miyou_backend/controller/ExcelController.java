package com.miyou.miyou_backend.controller;

import com.miyou.miyou_backend.common.ErrorCode;
import com.miyou.miyou_backend.common.ResultResponse;
import com.miyou.miyou_backend.model.vo.MiYouTableUserInfoVO;
import com.miyou.miyou_backend.once.importuser.TableListener;
import com.miyou.miyou_backend.service.ExcelService;
import com.miyou.miyou_backend.utlis.ExcelUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Excel 接口
 * @author LINGLAN
 */
@Log4j2
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Resource
    private ExcelService excelService;

    private CopyOnWriteArrayList<MiYouTableUserInfoVO> miYouTableUserInfos;

    public CopyOnWriteArrayList<MiYouTableUserInfoVO> getStudentList() {
        return miYouTableUserInfos;
    }

    public void setStudentList(CopyOnWriteArrayList<MiYouTableUserInfoVO> miyouTableUserInfos) {
        this.miYouTableUserInfos = miyouTableUserInfos;
    }

    /**
     * 上传 Excel
     *
     * @param file 上传文件
     * @return 得到的excel数据封装后进行返回
     */
    @RequestMapping("/upload")
    public ResultResponse excelUpload(MultipartFile file){
        ResultResponse response = checkParam(file);
        if (!(200 == response.getStatus())) {
            return response;
        }
        //调用service中的uploadExcel()获取excel中的数据
        List<MiYouTableUserInfoVO> students = excelService.uploadExcel(file,MiYouTableUserInfoVO.class,new TableListener());
        //将得到的excel数据封装后进行返回
        return ResultResponse.success(students);
    }

    /**
     * 检查上传文件参数
     *
     * @param file 上传文件
     * @return
     */
    private ResultResponse checkParam(MultipartFile file) {
        if (null == file) {
            return ResultResponse.fail("上传的文件为空");
        }
        String filename = file.getOriginalFilename();
        if (StringUtils.isEmpty(filename)) {
            return ResultResponse.fail("文件格式异常");
        }
        if (!filename.contains(".xlsx") && !filename.contains(".XLSX") ) {
            return ResultResponse.fail("文件格式错误");
        }
        return ResultResponse.success();
    }

    /**
     * 先从前端获取 miYouTableUserInfoVO
     *
     * @param miYouTableUserInfoVO miYouTableUser信息视图
     * todo 未实现
     */
    @RequestMapping("/getStudent")
    public void getStudent(@RequestBody CopyOnWriteArrayList<MiYouTableUserInfoVO> miYouTableUserInfoVO)  {
        setStudentList(miYouTableUserInfoVO);
    }

    /**
     * 实现excel下载功能
     *
     * @param response
     */
    @RequestMapping("/download")
    public void downloadExcel(HttpServletResponse response) {
        try {
            ExcelUtils.writeExcel("学生信息" ,MiYouTableUserInfoVO.class ,response,getStudentList());
        } catch (Exception e) {
            log.error("导出excel表格失败:", e);
        }
    }

}
