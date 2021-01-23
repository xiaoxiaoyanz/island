package com.wucc.island.common.pojo;

import lombok.Data;

@Data
public class BasePOJO {

	/**
	 * 数据来源（0：页面新增，1：数据库已存在，2：页面删除）
	 */
	private transient String sourceType = "1";
}
