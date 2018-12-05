package com.william.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

public class ReviewTest {
	
	/**
	 * ResultSet 封装 JDBC 的查询结果
	 */
	@Test
	public void testResultSet() {
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		try {
			// 1.获取数据库连接
			connection=getConnection();
			
			
			// 2.调用 Connection 对象的 createStatement() 方法获取 Statement 对象
			statement=connection.createStatement();
			
			// 3.准备 SQL 语句
			String sql="SELECT id,name,email,birth FROM customers";
			// 4.发送 SQL 语句： 调用 Statement 对象的 excuteQuery(sql) 方法。
			// 得到结果集对象 ResultSet
			resultSet=statement.executeQuery(sql);
			// 5. 处理结果集：
			// 5.1 调用 ResultSet 的 next() 方法 ：查看结果集的下一条记录是否有效，
			// 若有效则下移指针
			while (resultSet.next()) {
				// 5.2 getXxx() 方法获取具体的列的值。
				int id = resultSet.getInt(1);
				String name=resultSet.getString(2);
				String password=resultSet.getString(3);
				Date date=resultSet.getDate(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(password);
				System.out.println(date);
				
				System.out.println();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			releaseDB(resultSet, statement, connection);
		}
		
		
		
	
		
	}

	/**
	 * 1. Statement 是用于操作 SQL 的对象
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 * 
	 */
//	@Test
//	public void testStatement() throws ClassNotFoundException, Exception {
//
//		Connection connection = null;
//		Statement statement = null;
//
//		try {
//			// 1.获取数据库连接
//			connection = getConnection();
//			// 2.调用 Connection 对象的 createStatement() 方法获取 Statement 对象
//			statement = connection.createStatement();
//
//			// 3.准备 SQL 语句
//			String sql = "UPDATE customers SET name = 'ivan' " + "WHERE id= 4";
//
//			// 4.发送 SQL 语句： 调用 Statement 对象的 excuteUpdate(sql) 方法
//
//			statement.executeUpdate(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			// 5.关闭数据库资源： 由里向外关。
//			releaseDB(null, statement, connection);
//		}
//
//	}

	public void releaseDB(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// @Test
	// public void testGetConnection2() throws Exception {
	// Connection connection = getConnection();
	// System.out.println(connection);
	// }

	private Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		// 0.读取 jdbc.properties
		/**
		 * 1).属性文件对应 Java 中的 Properties 类 2).可以通过类加载器加载bin目录（类路径下）的文件
		 * 
		 */
		Properties properties = new Properties();
		InputStream inStream = ReviewTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(inStream);

		// 1.准备获取连接的4个字符串：user、password、jdbcUrl、driver
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String driverClass = properties.getProperty("driverClass");

		// 2.加载驱动：Class.forName(driverClass)
		Class.forName(driverClass);

		// 3.调用
		// DriverManager.getConnection(jdbcUrl,user,Password);
		// 获取数据库连接
		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}

	/**
	 * Connection 代表应用程序和数据库的一个连接。
	 * 
	 * @throws Exception
	 * 
	 */
	// @Test
	// public void testGetConnection() throws Exception {
	// // 1.准备获取连接的4个字符串：user、password、jdbcUrl、driver
	// String user = "root";
	// String password = "root";
	// String jdbcUrl = "jdbc:mysql:///jdbc";
	// String driverClass = "com.mysql.jdbc.Driver";
	//
	// // 2.加载驱动：Class.forName(driverClass)
	// Class.forName(driverClass);
	//
	// // 3.调用
	// // DriverManager.getConnection(jdbcUrl,user,Password);
	// // 获取数据库连接
	// Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
	// System.out.println(connection);
	//
	// }

}
