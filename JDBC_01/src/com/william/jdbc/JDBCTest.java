package com.william.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class JDBCTest {
	/**
	 * Driver是一个接口：数据库厂商必须提供实现的接口，从中获取数据库连接。
	 * 
	 * 1.加入Mysql数据驱动
	 *  1）解压mysql压缩包 
	 *  2）当前目录下新建lib目录 
	 *  3）把mysql-connector-java-5.1.39-bin.jar 复制到新建的lib目录下
     *  4）add to buildpath 加入到类路径下
	 * 
	 */
	@Test
	public void test() throws SQLException {
		//1. 创建一个Driver实现类的对象
		Driver driver = new com.mysql.jdbc.Driver();

		//2. 准备连接数据库的基本信息：url、us、password、
		String url = "jdbc:mysql://localhost:3306/jdbc";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "root");

		
		//3. 调用Driver借口的driver.connect(url, info)获取数据库连接
		Connection connection =driver.connect(url, info);
		System.out.println(connection);
		
	
	}
	
	/**
	 * 编写一个通用的方法，在不修改程序的情况下，可以获取任何数据库连接，
	 * 解决方案：把数据库驱动 Driver 实现类的全类名、url、us、password放入一个配置文件中，
	 * 通过修改数据库配置文件来实现具体的数据库的接解耦
	 * @throws Exception 
	 */
	public Connection getConnection() throws Exception {
		String driverClass=null;
		String jdbcUrl=null;
		String user=null;
		String password=null;
		
		//读取类路径下的jdbc.properties 文件。
		
		InputStream inStream=getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties=new Properties();
		properties.load(inStream);
		driverClass=properties.getProperty("driver");
		jdbcUrl=properties.getProperty("jdbcUrl");
		user=properties.getProperty("user");
		password=properties.getProperty("password");
		
		//通过反射创建Driver 对象。
		
		Driver driver=(Driver)Class.forName(driverClass).newInstance();
		
		Properties info=new Properties();
		info.put("user", user);
		info.put("password", password);
		
		//通过Driver的connect 方法获取数据库连接。
		Connection connection=driver.connect(jdbcUrl, info);
		return connection;
		
	}
	@Test
	public void testGetConnection() throws Exception {
		System.out.println(getConnection());
	}

}
