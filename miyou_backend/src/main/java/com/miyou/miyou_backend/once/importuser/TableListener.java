package com.miyou.miyou_backend.once.importuser;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.miyou.miyou_backend.model.vo.MiYouTableUserInfoVO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Excel 导入监听器
 *
 * 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 *
 * @author LINGLAN
 */
@Slf4j
public class TableListener implements ReadListener<MiYouTableUserInfoVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private List<MiYouTableUserInfoVO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * 解析数据
     *
     * @param data MiYou表用户信息
     * @param context 分析上下文
     */
    @Override
    public void invoke(MiYouTableUserInfoVO data, AnalysisContext context) {
        System.out.println(data);
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context 分析上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     *
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        log.info("存储数据库成功！");
    }
}
