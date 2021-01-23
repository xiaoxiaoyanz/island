package com.wucc.island.common.constant.enums;

/**
 * 单据状态类型
 *
 * @author zhoujiej
 * @version May 8, 2020
 */
public enum BillStatusEnum implements ValueTitleEnumI<String> {

    /*
     * 初始状态
     */
    INIT("-2", "初始状态"),

    /*
     * 草稿（暂存）
     */
    DRAFT("-1", "草稿"),

    /*
     * 待送审
     */
    PANDING_SUBMIT("0", "待送审"),

    /*
     * 流程中
     */
    IN_PROCESS("1", "流程中"),

    /*
     * 完成
     */
    COMPLETE("2", "完成");

    private final String value;

    private final String title;

    private BillStatusEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String title() {
        return title;
    }
}
