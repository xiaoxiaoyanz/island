package com.wucc.island.function;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class ToVarName implements TemplateMethodModelEx {
	// exec方法中可以传递多个参数
	@Override
	public Object exec(List args) throws TemplateModelException {
		String str = args.get(0).toString();
		return str.substring(0, 1).toLowerCase()
				+ str.substring(1, str.length() - 1);
	}

}
