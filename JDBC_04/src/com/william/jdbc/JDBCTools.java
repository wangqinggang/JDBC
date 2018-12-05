package com.william.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;



public class JDBCTools {

	/**
	 * 关闭  Statement 和 Connection
	 * @param statement
	 * @param connection
	 */
	public static void release(ResultSet resultSet,Statement statement, Connection connection) {
		if (resultSet!=null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 1.获取连接的方法。 通过读取配置文件从数据库服务器获取一个数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		// 1. 准备连接数据库的4个字符串
		// 1). 创建properties 对象
		Properties properties = new Properties();

		// 2). 创建jdbc.properties 对应的输入流
		InputStream inputStream = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
		// 3). 加载2)对应的输入流
		properties.load(inputStream);
		// 4). 具体决定user、password 等 4个字符串
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String driver = properties.getProperty("driver");
		// 2. 加载数据库驱动程序（对应的 Driver 实现类中有注册驱动的静态代码块。）
		Class.forName(driver);

		// 3. 通过 DriverManager 的 getConnection() 方法获取数据库连接。
		return DriverManager.getConnection(jdbcUrl, user, password);
	}
}
