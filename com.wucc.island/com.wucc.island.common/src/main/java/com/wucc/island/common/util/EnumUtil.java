package com.wucc.island.common.util;

import com.wucc.island.common.constant.enums.ValueTitleEnumI;
import com.wucc.island.common.supervo.CommonVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 枚举操作工具类
 *
 * @author zhoujiej
 * @date 2020/05/28
 */
public class EnumUtil {

    /**
     * 枚举转值级
     *
     * @param enumClass
     *            枚举class
     * @return List<CommonVO>
     */
    public static List<CommonVO> toCommons(Class<? extends ValueTitleEnumI<String>> enumClass) {
        List<CommonVO> list = new ArrayList<CommonVO>();
        ValueTitleEnumI<String>[] enumConstants = enumClass.getEnumConstants();
        for (ValueTitleEnumI<String> valueTitleEnumI : enumConstants) {
            CommonVO commonVO = new CommonVO();
            commonVO.setId(valueTitleEnumI.value());
            commonVO.setCode(valueTitleEnumI.value());
            commonVO.setName(valueTitleEnumI.title());
            list.add(commonVO);
        }
        return list;
    }

    /**
     * 枚举转Map
     *
     * @param enumClass
     *            枚举class
     * @return Map<String, String>
     */
    public static Map<String, String> toMap(Class<? extends ValueTitleEnumI<String>> enumClass) {
        ValueTitleEnumI<String>[] enumConstants = enumClass.getEnumConstants();
        Map<String, String> map = new HashMap<>(16);
        for (ValueTitleEnumI<String> valueTitleEnumI : enumConstants) {
            map.put(valueTitleEnumI.value(), valueTitleEnumI.title());
        }
        return map;
    }
}
