package com.wucc.island.common.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class SystemProperties {

    public static String SYS_TYPE;

    public static Integer BATCH_COUNT;

    public static String ENABLE_NEW_DEP;

    public static boolean ENABLE_FILTER_WORKFLOW;

    public static boolean ENABLE_BUTTON_AUTH;

    public static String DB_NAME;

    public static boolean ENABLE_CUSTOM_AGENCY;

    @Value("${enableNewDep:0}")
    public void setSeNABLEnEWdEP(String enableNewDep) {
        if (StringUtils.isEmpty(enableNewDep)) {
            enableNewDep = "0";
        }
        SystemProperties.ENABLE_NEW_DEP = enableNewDep;
    }

    @Value("${sysType}")
    public void setStoreType(String sysType) {
        if (StringUtils.isEmpty(sysType)) {
            sysType = "apsl";
        }
        SystemProperties.SYS_TYPE = sysType;
    }

    @Value("${batchCount:200}")
    public void setBatchCount(Integer batchCount) {
        SystemProperties.BATCH_COUNT = batchCount;
    }

    @Value("${enableFilterWorkflow:true}")
    public void setEnableFilterWorkflow(boolean enableFilterWorkflow) {
        SystemProperties.ENABLE_FILTER_WORKFLOW = enableFilterWorkflow;
    }

    @Value("${enableButtonAuth:true}")
    public void setEnableButtonAuth(boolean enableButtonAuth) {
        SystemProperties.ENABLE_BUTTON_AUTH = enableButtonAuth;
    }

    @Value("${dbname:mysql}")
    public void setDbName(String dbname) {
        SystemProperties.DB_NAME = dbname;
    }

    @Value(("${enableCustomAgency:false}"))
    public void setEnableCustomAgency(boolean enableCustomAgency) {
        SystemProperties.ENABLE_CUSTOM_AGENCY = enableCustomAgency;
    }

    private SystemProperties() {

    }
}
