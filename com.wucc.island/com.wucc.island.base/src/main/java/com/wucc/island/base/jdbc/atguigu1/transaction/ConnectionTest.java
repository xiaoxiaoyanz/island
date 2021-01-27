package com.wucc.island.base.jdbc.atguigu1.transaction;

import java.sql.Connection;

import com.wucc.island.base.jdbc.atguigu4.util.JDBCUtils;
import org.junit.Test;



public class ConnectionTest {
	
	@Test
	public void testGetConnection() throws Exception{
		Connection conn = JDBCUtils.getConnection();
		System.out.println(conn);
	}
}
