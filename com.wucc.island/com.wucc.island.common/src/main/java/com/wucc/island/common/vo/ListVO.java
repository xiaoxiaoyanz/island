package com.wucc.island.common.vo;

public abstract class ListVO extends ResponseVO {

    /**
     * 数据来源（0：页面新增，1：数据库已存在，2：页面删除）
     */
    private transient String sourceType = "1";

    /**
     * 当前行是否可编辑
     */
    private transient String rowEditable = "1";

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(String rowEditable) {
        this.rowEditable = rowEditable;
    }

}
