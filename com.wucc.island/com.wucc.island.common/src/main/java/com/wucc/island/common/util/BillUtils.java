package com.wucc.island.common.util;

import java.util.UUID;

public abstract class BillUtils {

	public static String getCharPrimaryKey() {
		return UUID.randomUUID().toString();
	}

	public static Long getBigIntPrimaryKey() {
		return 0L;
	}
}
