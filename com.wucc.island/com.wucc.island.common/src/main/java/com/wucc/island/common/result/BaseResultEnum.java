package com.wucc.island.common.result;

/**
 * 返回结果
 *
 * @author Hujhb
 *
 */
public enum BaseResultEnum implements BaseResultEnumI {

    ERROR_LOGIN(0, "请重新登录"),

    SUCCESS(0, "操作成功"),

    UNKNOWN_ERROR(-1, "系统出现异常"),

    /* 示例：后续补充其他错误信息 */
    ERROR_XX_10001(-10001, "XX业务的XX错误"),

    ERROR_SYS_20001(-20001, "系统配置错误"), ERROR_SYS_20002(-20002, "新增失败"), ERROR_SYS_20003(-20003, "保存失败"),
    ERROR_SYS_20004(-20004, "修改失败"), ERROR_SYS_20005(-20005, "删除失败"), ERROR_SYS_20006(-20006, "送审失败"),
    ERROR_SYS_20007(-20007, "审核失败"), ERROR_SYS_20008(-20008, "退回失败"), ERROR_SYS_20009(-20009, "复制失败"),
    ERROR_SYS_20010(-20010, "当前数据已发生变化,请刷新后重试"), ERROR_SYS_20011(-20011, "参数错误"), ERROR_SYS_20012(-20012, "导入失败"),
    ERROR_SYS_20013(-20013, "查询数据为空"), ERROR_SYS_20014(-20014, "操作失败"), ERROR_SYS_20015(-20015, "导入失败，请确认模板是否已发生变化"),
    ERROR_SYS_20016(-20016, "非业务用户不允许进行初始化操作"), ERROR_SYS_20017(-20017, "编码规则要素总长度不能大于40"),
    ERROR_SYS_20018(-20018, "编码规则不存在或存在多个"), ERROR_SYS_20019(-20019, "编码规则要素[单位编码]长度过长"),
    ERROR_SYS_20020(-20020, "预生成编码失败"), ERROR_SYS_20021(-20021, "监听补货通知队列程序出错"), ERROR_SYS_20022(-20022, "获取编码失败"),
    ERROR_SYS_20023(-20020, "导入数据重复!"), ERROR_DATA_30001(-300001, "没有数据权限"),

    WARNING_SYS_90001(90001, "编码已存在");

    private Integer code;

    private String msg;

    BaseResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
