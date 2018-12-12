package com.william.jdbc;


import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
/**
 * 测试 DBUtils 工具类
 * @author thinkpad
 *
 */
public class DBUtilsTest {
	/**
	 * 测试 QueryRunner 类的 Update 方法
	 */
	@Test
	public void testQueryRunnerUpdate() {
		//1. 创建QueryRunner 的实现类
		QueryRunner queryRunner=new QueryRunner();
		
		//2. 使用其 update 方法
		String sql ="DELETE FROM customers "+"WHERE id IN(?,?)";
		
		Connection connection=null;
		try {
			connection=JDBCTools.getConnection();
			queryRunner.update(connection, sql, 1,2);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCTools.releaseDB(null, null, connection);
		}
		
		
	}

}
