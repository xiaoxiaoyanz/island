package com.wucc.island.common.util;

import com.wucc.island.common.context.Context;
import com.wucc.island.common.context.ContextInfo;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * @author 作者 yinyla@yonyou.com:
 * 
 * @version 创建时间：Aug 21, 2019 2:47:09 PM
 * 
 *          封装上下文对象
 */
public class ContextInfoPackage {

    public static <T> void pkg(T t) {
        Date now = Calendar.getInstance().getTime();
        ContextInfo info = Context.getContextInfo();
        String orgId = info.getOrgID();
        String orgCode = info.getOrgCode();
        String orgName = info.getOrgName();
        String userId = info.getUserID();
        String userName = info.getUserName();
        String deptId = info.getDepID();
        String deptCode = info.getDepCode();
        String deptName = info.getDepName();
        String rgCode = info.getRegion();
        Integer year = info.getYear();
        Class clazz = t.getClass();
        try {
            Method setOrgId = clazz.getMethod("setOrgId", String.class);
            setOrgId.invoke(t, orgId);

            Method setOrgCode = clazz.getMethod("setOrgCode", String.class);
            setOrgCode.invoke(t, orgCode);

            Method setOrgName = clazz.getMethod("setOrgName", String.class);
            setOrgName.invoke(t, orgName);

            Method setDeptId = clazz.getMethod("setDeptId", String.class);
            setDeptId.invoke(t, deptId);

            Method setDeptCode = clazz.getMethod("setDeptCode", String.class);
            setDeptCode.invoke(t, deptCode);

            Method setDeptName = clazz.getMethod("setDeptName", String.class);
            setDeptName.invoke(t, deptName);

            Method setRgCode = clazz.getMethod("setRgCode", String.class);
            setRgCode.invoke(t, rgCode);

            Method setSetYear = clazz.getMethod("setSetYear", Integer.class);
            setSetYear.invoke(t, year);

            Method setCreatorId = clazz.getMethod("setCreatorId", String.class);
            setCreatorId.invoke(t, userId);

            Method setCreator = clazz.getMethod("setCreator", String.class);
            setCreator.invoke(t, userName);

            Method setCreationtime = clazz.getMethod("setCreationtime", Date.class);
            setCreationtime.invoke(t, now);

            Method setModifierId = clazz.getMethod("setModifierId", String.class);
            setModifierId.invoke(t, userId);

            Method setModifier = clazz.getMethod("setModifier", String.class);
            setModifier.invoke(t, userName);

            Method setModifiedtime = clazz.getMethod("setModifiedtime", Date.class);
            setModifiedtime.invoke(t, now);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
