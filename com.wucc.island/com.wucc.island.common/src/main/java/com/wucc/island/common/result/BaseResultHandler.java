package com.wucc.island.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wucc.island.common.vo.ResponseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 返回结果工具类
 * 
 * @author Hujhb
 *
 */
public class BaseResultHandler {

    public static BaseResult success() {
        return success(null);
    }

    @SuppressWarnings("unchecked")
    public static BaseResult success(Object obj) {
        BaseResult result = new BaseResult();
        result.setFlag("SUCCESS");
        result.setCode(BaseResultEnum.SUCCESS.getCode());
        result.setMsg(BaseResultEnum.SUCCESS.getMsg());
        /* mybatisplus分页插件 */
        if (obj != null && obj instanceof IPage) {
            IPage data = (IPage)obj;
            result.setData(new BaseResultPage(data));
            return result;
        }
        if (obj != null && obj instanceof Page) {
            Page data = (Page)obj;
            result.setData(new BaseResultPage(data));
            return result;
        }
        if (obj != null && obj instanceof List) {
            List list = (List)obj;
            if (!list.isEmpty() && list.get(0) instanceof ResponseVO) {
                int i = 1;
                for (Object object : list) {
                    ((ResponseVO)object).setSequNo(i++);
                }
            }
        }
        result.setData(obj);
        return result;
    }

    // 异常返回
    public static BaseResult error(BaseResultEnumI num) {
        BaseResult result = new BaseResult();
        result.setFlag("ERROR");
        result.setCode(num.getCode());
        result.setMsg(num.getMsg());
        return result;
    }

    // 异常返回
    public static BaseResult error(Integer code, String msg) {
        BaseResult result = new BaseResult();
        result.setFlag("ERROR");
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    // 异常返回
    public static BaseResult errorData(Object Data) {
        BaseResult result = new BaseResult();
        result.setFlag("ERROR");
        result.setData(Data);
        return result;
    }

    // 未知异常返回结果
    public static BaseResult unknownError() {
        BaseResult result = new BaseResult();
        result.setFlag("ERROR");
        result.setCode(BaseResultEnum.UNKNOWN_ERROR.getCode());
        result.setMsg(BaseResultEnum.UNKNOWN_ERROR.getMsg());
        return result;
    }

    /**
     * 创建分页请求简单示例，业务上按照自己的需求修改.
     */
    public static PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        return PageRequest.of(pageNumber, pagzSize, sort);
    }
}
