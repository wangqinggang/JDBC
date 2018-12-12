package com.william.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 访问数据的 DAO 接口
 * 里面定义好访问数据表的各种方法
 * @author thinkpad
 * @param T: DAO 处理的实体类的类型。
 *
 */
public interface Dao<T> {
	
	/**
	 * 批量处理的方法
	 * @param connection
	 * @param sql
	 * @param args：填充占位符的 Object[] 类型的可变参数。
	 */
	void batch(Connection connection,String sql,Object []...args);
	/**
	 * 返回具体的一个值，例如总人数，平均工资，某一个人的email 等
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	<E> E getForValue(Connection connection,String sql,Object ...args);
	/**
	 * 返回 T 的一个集合
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> getForList(Connection connection,String sql,Object ...args);
	/**
	 * 返回一个 T 对象
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	T get(Connection connection,String sql,Object ...args)throws SQLException;
	/**
	 * INSERT, UPDATE, DELETE
	 * @param connection:数据库连接
	 * @param sql：SQL语句
	 * @param args：填充占位符的可变参数
	 */
	void update(Connection connection,String sql,Object ...args);
}
