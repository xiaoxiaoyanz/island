package com.wucc.island.common.supervo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 要素父级封装类
 *
 * @author zhoujiej
 * @date 2020/05/27
 */
@Data
public class CommonVO implements Serializable {

    private static final long serialVersionUID = 2825094069487378839L;

    /**
     * 主键
     */
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 映射要素值集
     */
    private List<String> refValue;

    /**
     * 上级节点ID
     */
    private String parentId;

    /**
     * 层级
     */
    private Integer levelNum;

    /**
     * 是否叶子节点
     */
    private String leaf;

    /**
     * 是否被勾选
     */
    private Boolean checked;
}
