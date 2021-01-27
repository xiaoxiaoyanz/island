package com.wucc.island.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Controller请求返回对象
 * 
 * @author Hujhb
 *
 * @param <T>
 */
@ApiModel(description = "返回结果封装类")
public class
BaseResult<T> implements Serializable {

	@ApiModelProperty("返回结果")
	private String flag;

	@ApiModelProperty("返回结果代码")
	private Integer code;

	@ApiModelProperty("提示信息")
	private String msg;

	@ApiModelProperty("返回数据")
	private T data;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
