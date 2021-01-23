package com.wucc.island.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wucc.island.common.vo.ResponseVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 
 * @author 作者 yinyla@yonyou.com:
 * 
 * @version 创建时间：Jun 14, 2019 9:20:54 AM
 * 
 *          封装返回数据，若不封装会有page对象序列化问题
 * 
 */
public class BaseResultPage {

    private List<?> content;

    private long totalElements;

    private int size;

    public BaseResultPage(Page page) {
        int pageIndex = page.getNumber();
        int pageSize = page.getSize();
        List list = page.getContent();
        setSequNo(list, pageIndex, pageSize);
        this.content = list;
        this.size = pageSize;
        this.totalElements = page.getTotalElements();
    }

    private void setSequNo(List list, int pageIndex, int pageSize) {
        if (!list.isEmpty() && list.get(0) instanceof ResponseVO) {
            int i = 1 + pageSize * pageIndex;
            for (Object object : list) {
                ((ResponseVO)object).setSequNo(i++);
            }
        }
    }

    public BaseResultPage(IPage<?> page) {
        int pageIndex = (int)page.getCurrent();
        int pageSize = (int)page.getSize();
        List list = page.getRecords();
        setSequNo(list, pageIndex - 1, pageSize);
        this.content = list;
        this.size = pageSize;
        this.totalElements = page.getTotal();
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
