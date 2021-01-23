package com.wucc.island.common.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Context {

    private final static String CONTEXT_KEY = "CONTEXT-INFO";
    private final static String EXT_PREFIX = "EXT-";

    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>() {
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public static ContextInfo getContextInfo() {
        ContextInfo contextInfo = (ContextInfo)(threadLocal.get().get(CONTEXT_KEY));
        // /以下代码只是供开发用，和平台集成后需要删除
        if (null == contextInfo) {
            contextInfo = new ContextInfo();
            contextInfo.setUserID("70ec01ea-9fa9-41fd-ba78-2b1987b392f2");
            contextInfo.setUserName("数据管理员");
            contextInfo.setOrgID("f2589d18720a415c94789f0461037f6a");
            contextInfo.setOrgCode("001");
            contextInfo.setOrgName("测试单位");
            contextInfo.setDepID("003");
            contextInfo.setDepCode("D003");
            contextInfo.setDepName("部门003");
            contextInfo.setRegion("87");
            contextInfo.setFinance(false);
            contextInfo.setCurrentMenuId("regist_book");
            Set<String> role = new HashSet<String>();
            String roleId = "e98be6ec-5421-11e9-b5ad-6c92bf091900";
            contextInfo.setYear(2020);
            contextInfo.setPeriodmonth("01");
            contextInfo.setRoleId(roleId);
            setContextInfo(contextInfo);
        }

        return contextInfo;
    }

    public static void setContextInfo(ContextInfo contextInfo) {
        threadLocal.get().put(CONTEXT_KEY, contextInfo);
    }

    public static Object getExtendAttribute(String key) {
        return threadLocal.get().get(EXT_PREFIX + key);
    }

    public static void setExtendAttribute(String key, Object obj) {
        threadLocal.get().put(key, obj);
    }

    public static void destroy() {
        threadLocal.get().clear();
    }

}
