package com.william.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


import org.junit.Test;

public class JDBCTest {
	/**
	 * ResultSet :结果集，封装了 JDBC 进行查询的结果。
	 * 1. 调用 Statement 的 excuteQuery(sql) 可以得到结果集。
	 * 2. ResultSet 返回的实际上就是一张数据表，有一个指针指向数据表第一行的前面
	 * 可以调用  next() 方法检测下一行是否有效。若有效该方法返回 true,且指针下移，
	 * 相当于Iterator 对象的 hasNext() 和 next() 方法的结合体。
	 * 3. 当指针对位到一行时，例如：getXxx(index)、 或 getXxx(columnName)
	 * 获取每一行的列的值，例如： getInt(1),getString("name")
	 * 4. ResultSet 当然也需要进行关闭。
	 *  
	 */
	@Test
	public void testResultSet() {
		//获取id=4 的customer 数据表的记录，并打印
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		
		try {
			//1.获取connection
			connection=JDBCTools.getConnection();
			//2.获取Statement
			statement=connection.createStatement();
			//3.准备SQL
			String sql="SELECT id,name,email,birth"+
			" FROM customers ";// WHERE id=4
			//4.执行查询，得到 ResultSet
			resultSet=statement.executeQuery(sql);
			//5.处理 ResultSet
			while (resultSet.next()) {
				int id=resultSet.getInt(1);
				String name=resultSet.getString("name");
				String email=resultSet.getString(3);
				Date date=resultSet.getDate(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(email);
				System.out.println(date);
			}
			//6.关闭数据库资源
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(resultSet, statement, connection);
		}
		
		
	}
	
	/**
	 * 通用的更新的方法：包括 INSERT、UPDATE、DELETE
	 * 版本 1.
	 * @param sql
	 */
	public void update(String sql) {
		Connection connection=null;
		Statement statement=null;
		try {
			connection=JDBCTools.getConnection();
			statement=connection.createStatement();
			statement.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(null,statement, connection);
		}
		
	}
	
	/**
	 * 通过JDBC 向指定的数据库插入一条数据
	 * 1. Statement: 用于执行SQL 语句的对象
	 * 1).通过 Connection 的 CreateStatement() 对象来获取
	 * 2).通过excuteUpdate(sql) 可以执行SQL语句。
	 * 3).传入的 SQL 可以是 INSERT、UPDATE、DELETE，但不能是 SELECT
	 * 
	 * 2. Connection、Statement 都是应用程序和数据库服务器的连接资源，使用后一定要关闭
	 * 需要在 finally 中关闭 Connection 和 Statement 对象
	 * 
	 * 3. 关闭的顺序是：先关闭后获取的，即先关闭 Statement 后关闭 Connection
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	@Test
	public void testStatement() throws ClassNotFoundException, IOException, Exception {
		//1.获取数据库连接
		Connection connection=null;
		Statement statement=null;

		try {
			connection=JDBCTools.getConnection();
			
			//3.准备插入的SQL语句
			String sql=null;
//			sql="INSERT INTO customers (name,email,birth) "+
//			"VALUES('xyz','ivan@821.com','1990-12-12');";
			
//			sql="DELETE From customers WHERE id = 3";
			
			sql="UPDATE customers SET name='tom'"+
			" WHERE id=4";
			
			System.out.println(sql);
			//4.执行插入 
			//1).获取sql语句的Statement对象:
			//调用 Connection 对象的 createStatement 方法来获取
			statement=connection.createStatement();
			
			//2）.调用statement 对象的 executeUpdate（sql） 执行 SQL 语句插入
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTools.release(null,statement, connection);
		}
		
		
	}
	
	
	public Connection getConnection2() throws Exception {
		//1. 准备连接数据库的4个字符串
		//1). 创建properties 对象
		Properties properties=new Properties();
		
		//2). 创建jdbc.properties 对应的输入流
		InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		//3). 加载2)对应的输入流
		properties.load(inputStream);
		//4). 具体决定user、password 等 4个字符串
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		String jdbcUrl=properties.getProperty("jdbcUrl");
		String driver=properties.getProperty("driver");
		//2. 加载数据库驱动程序（对应的 Driver 实现类中有注册驱动的静态代码块。）
		Class.forName(driver);
		
		//3. 通过 DriverManager 的 getConnection() 方法获取数据库连接。
		return DriverManager.getConnection(jdbcUrl,user,password);
	}
	
//	/**
//	 * DriverManager 是驱动类的管理类。
//	 * 1）可以通过重载的 getConnection() 方法获取数据库连接，较为方便
//	 * 2）可以同时管理多个驱动程序：调用方法时传入的参数不同，即返回不同的数据库连接。
//	 * 
//	 * @throws SQLException 
//	 * @throws IOException 
//	 * @throws ClassNotFoundException 
//	 */
//	@Test
//	public void testDriverManager() throws SQLException, IOException, ClassNotFoundException {
//		// 1.准备连接数据库的4个字符串。
//		//驱动的全类名
//		String driverClass="com.mysql.jdbc.Driver";
//		//JDBC url
//		String jdbcUrl="jdbc:mysql://localhost:3306/jdbc";
//		//user
//		String user="root";
//		//password
//		String password="root";
//		
//		// 2. 加载数据库驱动程序(对应的Driver 驱动中有对应的静态代码快)。
//		//DriverManager.registerDriver(Class.forName(driverClass).newInstance());
//		Class.forName(driverClass);
//		
//		// 3. 通过 DriverManager 的 getConnection() 方法获取数据库连接。
//		Connection connection= DriverManager.getConnection(jdbcUrl, user, password);
//		
//		System.out.println(connection);
//	}
//	
//	
//	/**
//	 * Driver是一个接口：数据库厂商必须提供实现的接口，从中获取数据库连接。
//	 * 
//	 * 1.加入Mysql数据驱动
//	 *  1）解压mysql压缩包 
//	 *  2）当前目录下新建lib目录 
//	 *  3）把mysql-connector-java-5.1.39-bin.jar 复制到新建的lib目录下
//     *  4）add to buildpath 加入到类路径下
//	 * 
//	 */
//	@Test
//	public void test() throws SQLException {
//		//1. 创建一个Driver实现类的对象
//		Driver driver = new com.mysql.jdbc.Driver();
//
//		//2. 准备连接数据库的基本信息：url、us、password、
//		String url = "jdbc:mysql://localhost:3306/jdbc";
//		Properties info = new Properties();
//		info.put("user", "root");
//		info.put("password", "root");
//
//		
//		//3. 调用Driver借口的driver.connect(url, info)获取数据库连接
//		Connection connection =driver.connect(url, info);
//		System.out.println(connection);
//		
//	
//	}
//	
//	/**
//	 * 编写一个通用的方法，在不修改程序的情况下，可以获取任何数据库连接，
//	 * 解决方案：把数据库驱动 Driver 实现类的全类名、url、us、password放入一个配置文件中，
//	 * 通过修改数据库配置文件来实现具体的数据库的接解耦
//	 * @throws Exception 
//	 */
//	public Connection getConnection() throws Exception {
//		String driverClass=null;
//		String jdbcUrl=null;
//		String user=null;
//		String password=null;
//		
//		//读取类路径下的jdbc.properties 文件。
//		
//		InputStream inStream=getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//		Properties properties=new Properties();
//		properties.load(inStream);
//		driverClass=properties.getProperty("driver");
//		jdbcUrl=properties.getProperty("jdbcUrl");
//		user=properties.getProperty("user");
//		password=properties.getProperty("password");
//		
//		//通过反射创建Driver 对象。
//		
//		Driver driver=(Driver)Class.forName(driverClass).newInstance();
//		
//		Properties info=new Properties();
//		info.put("user", user);
//		info.put("password", password);
//		
//		//通过Driver的connect 方法获取数据库连接。
//		Connection connection=driver.connect(jdbcUrl, info);
//		return connection;
//		
//	}
//	@Test
//	public void testGetConnection() throws Exception {
//		System.out.println(getConnection());
//	}

}
