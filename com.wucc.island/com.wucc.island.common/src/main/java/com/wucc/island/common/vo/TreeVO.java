package com.wucc.island.common.vo;

import com.wucc.island.common.constant.consts.WhetherConsts;

import java.util.List;



/**
 * 树展示视图
 * 
 * @author zhoujiej
 * @version May 13, 2020
 */
public class TreeVO<T> extends ResponseVO {

    /**
     * 节点唯一标识
     */
    private String key;

    /**
     * 节点显示名称
     */
    private String title;

    /**
     * 父节点标识
     */
    private String ParentKey;

    /**
     * 节点层级
     */
    private Integer levelNum;

    /**
     * 是否叶子节点（1：是；0：否）
     */
    private String leaf;

    /**
     * 是否叶子节点（1：是；0：否）
     */
    private boolean hasChildren;

    /**
     * 子节点集合
     */
    private List<T> children;

    /**
     * 当前行是否可编辑
     */
    private transient String rowEditable = "1";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentKey() {
        return ParentKey;
    }

    public void setParentKey(String parentKey) {
        ParentKey = parentKey;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
        if (WhetherConsts.YES.equals(leaf)) {
            this.hasChildren = false;
        } else {
            this.hasChildren = true;
        }
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public String getRowEditable() {
        return rowEditable;
    }

    public void setRowEditable(String rowEditable) {
        this.rowEditable = rowEditable;
    }

}
