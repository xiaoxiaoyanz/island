package com.wucc.island.common.context;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class ContextInfo implements Serializable {

    private static final long serialVersionUID = -8488797084256365886L;
    /**
     * 主用户ID
     */
    private String masterUserID;
    /**
     * 当前用户及状态
     */
    private String userID;
    private String userCode;
    private String userName;

    // 用户状态 1：已注册，2：已实名 3：已通过审核
    private String userStatus;

    // 安全状态 0 初始密码修改 1正常
    private String safeStatus;

    // 用户类型 0 普通用户 1企业用户 3公共数据中心用户 4产品管理用户 5运营服务中心 6 云管理账号
    private String userTypeCode;

    // 用户职责 0平台类 1普通业务类 2单位管理类（单位管理 ）
    private String userResponsibility;

    // 是否业务管理员
    private boolean bm = false;

    // 是否系统管理员
    private boolean sm = false;

    private String roleId;// 角色ID

    private String roleCode; // 角色编码
    /**
     * 当前用户身份信息
     */
    private String tenantID = "";// 租户
    private String region = "";// 区划
    private String orgID;// 单位
    private String orgCode;
    private String orgName;
    private String belongOrgId;// 所属单位ID
    private String belongOrgCode;// 所属单位编码
    private String belongOrgName;// 所属单位名称
    private String orgTypeCode;// 单位类型编码
    private String orgTypeName;// 单位类型名称
    private Boolean self;// 是否主管单位本级
    private String parentOrgCode;// 上级部门编码
    private String depID;// 部门
    private String depCode;
    private String depName;
    private String depParentID;// 上级部门
    private String postID;// 岗位
    private String postCode;
    private String postName;

    /**
     * 业务信息
     */
    private int year;// 年度
    private String accBookID;// 账套
    private String accBookCode;

    private String accperiodId; // 会计期间
    private String periodmonthId;// 会计月份
    private String periodmonth;

    /**
     * 员工信息 （具体的人）
     */
    private String employeeId;// 员工ID
    /**
     * 联系方式 手机、电话
     */
    private String MOB;
    private String TEL;

    /**
     * 当前菜单、功能信息
     */

    private String currentMenuId;
    private String currentMenuCode;
    private String currentMenuUri;
    private String currentModuleId;
    private String currentModuleCode;
    private String currentReOrgId;

    // 下级单位此处保存财政单位的ID
    private String financeOrgId;
    // 其他信息扩展
    private boolean finance = false;

    private String unittypeCode;

    // 是否为部门管理员，若为部门管理员则按部门查询
    private boolean isDeptManager;

    // 系统类型，标识当前是固定资产还是低值易耗品之类的
    private String sysType;

    // 系统时间
    private Date sysTime;

    private Object obj;
    /**
     * 当前用户身份信息
     */
    private String coCode;
    private String coName;

    /**
     * 业务信息
     */
    private Integer setYear;// 年度

    private Integer fiscalYear;

    private String rgCode;

    private String distCode;

    /**
     * 添加行政区划名称
     */
    private String distName;

    private Map<String, Object> sessionMap;
    private String tokenId;

    // 模板列表ID
    private String templateListId;

    // 模板表单ID
    private String templateFormId;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Boolean getSelf() {
        return self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }

    public String getParentOrgCode() {
        return parentOrgCode;
    }

    public void setParentOrgCode(String parentOrgCode) {
        this.parentOrgCode = parentOrgCode;
    }

    public String getTemplateListId() {
        return templateListId;
    }

    public void setTemplateListId(String templateListId) {
        this.templateListId = templateListId;
    }

    public String getTemplateFormId() {
        return templateFormId;
    }

    public void setTemplateFormId(String templateFormId) {
        this.templateFormId = templateFormId;
    }

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getCurrentMenuUri() {
        return currentMenuUri;
    }

    public void setCurrentMenuUri(String currentMenuUri) {
        this.currentMenuUri = currentMenuUri;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public boolean isDeptManager() {
        return roleCode == null ? false : roleCode.equals("farole02");
    }

    public void setDeptManager(boolean isDeptManager) {
        this.isDeptManager = isDeptManager;
    }

    public String getFinanceOrgId() {
        return financeOrgId;
    }

    public void setFinanceOrgId(String financeOrgId) {
        this.financeOrgId = financeOrgId;
    }

    public String getMasterUserID() {
        return masterUserID;
    }

    public void setMasterUserID(String masterUserID) {
        this.masterUserID = masterUserID;
    }

    public String getUnittypeCode() {
        return unittypeCode;
    }

    public void setUnittypeCode(String unittypeCode) {
        this.unittypeCode = unittypeCode;
    }

    public String getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(String userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTenantID() {
        return tenantID;
    }

    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    public String getOrgID() {
        if (StringUtils.isNotEmpty(orgID)) {
            return orgID;
        }
        return "*";
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDepID() {
        return depID;
    }

    public void setDepID(String depID) {
        this.depID = depID;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAccBookID() {
        return accBookID;
    }

    public void setAccBookID(String accBookID) {
        this.accBookID = accBookID;
    }

    public String getAccBookCode() {
        return accBookCode;
    }

    public void setAccBookCode(String accBookCode) {
        this.accBookCode = accBookCode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public String getCurrentMenuId() {
        return currentMenuId;
    }

    public void setCurrentMenuId(String currentMenuId) {
        this.currentMenuId = currentMenuId;
    }

    public String getCurrentMenuCode() {
        return currentMenuCode;
    }

    public void setCurrentMenuCode(String currentMenuCode) {
        this.currentMenuCode = currentMenuCode;
    }

    public String getCurrentModuleId() {
        return currentModuleId;
    }

    public void setCurrentModuleId(String currentModuleId) {
        this.currentModuleId = currentModuleId;
    }

    public String getCurrentModuleCode() {
        return currentModuleCode;
    }

    public void setCurrentModuleCode(String currentModuleCode) {
        this.currentModuleCode = currentModuleCode;
    }

    public String getCurrentReOrgId() {
        return currentReOrgId;
    }

    public void setCurrentReOrgId(String currentReOrgId) {
        this.currentReOrgId = currentReOrgId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMOB() {
        return MOB;
    }

    public void setMOB(String mOB) {
        MOB = mOB;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String tEL) {
        TEL = tEL;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepParentID() {
        return depParentID;
    }

    public void setDepParentID(String depParentID) {
        this.depParentID = depParentID;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserResponsibility() {
        return userResponsibility;
    }

    public void setUserResponsibility(String userResponsibility) {
        this.userResponsibility = userResponsibility;
    }

    public boolean isBm() {
        return bm;
    }

    public void setBm(boolean bm) {
        this.bm = bm;
    }

    public boolean isSm() {
        return sm;
    }

    public void setSm(boolean sm) {
        this.sm = sm;
    }

    public String getSafeStatus() {
        return safeStatus;
    }

    public void setSafeStatus(String safeStatus) {
        this.safeStatus = safeStatus;
    }

    public String getAccperiodId() {
        return accperiodId;
    }

    public void setAccperiodId(String accperiodId) {
        this.accperiodId = accperiodId;
    }

    public String getPeriodmonthId() {
        return periodmonthId;
    }

    public void setPeriodmonthId(String periodmonthId) {
        this.periodmonthId = periodmonthId;
    }

    public String getPeriodmonth() {
        return periodmonth;
    }

    public void setPeriodmonth(String periodmonth) {
        this.periodmonth = periodmonth;
    }

    public String getOrgTypeCode() {
        return orgTypeCode;
    }

    public void setOrgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    @Override
    public String toString() {
        return "ContextInfo [userID=" + userID + ", year=" + year + ", sysType=" + sysType + "]";
    }

    public String getCoCode() {
        return coCode;
    }

    public void setCoCode(String coCode) {
        this.coCode = coCode;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public Integer getSetYear() {
        return setYear;
    }

    public void setSetYear(Integer setYear) {
        this.setYear = setYear;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getRgCode() {
        return rgCode;
    }

    public void setRgCode(String rgCode) {
        this.rgCode = rgCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getBelongOrgId() {
        return belongOrgId;
    }

    public void setBelongOrgId(String belongOrgId) {
        this.belongOrgId = belongOrgId;
    }

    public String getBelongOrgCode() {
        return belongOrgCode;
    }

    public void setBelongOrgCode(String belongOrgCode) {
        this.belongOrgCode = belongOrgCode;
    }

    public String getBelongOrgName() {
        return belongOrgName;
    }

    public void setBelongOrgName(String belongOrgName) {
        this.belongOrgName = belongOrgName;
    }
}
