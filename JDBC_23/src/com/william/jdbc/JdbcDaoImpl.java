package com.william.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * 使用 QueryRunner 提供其具体的实现
 * @author thinkpad
 *
 * @param <T>：子类需传入的泛型类型
 */
public class JdbcDaoImpl<T> implements Dao<T>{
	
	private QueryRunner queryRunner=null;
	private Class<T> type;
	
	public  JdbcDaoImpl() {
		queryRunner=new QueryRunner();
		type=ReflectionUtils.getSuperGenericType(getClass());
	}
	@Override
	public void batch(Connection connection, String sql, Object[]... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E> E getForValue(Connection connection, String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(Connection connection, String sql, Object... args) throws SQLException {
		
		return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
	}

	@Override
	public void update(Connection connection, String sql, Object... args) {
		// TODO Auto-generated method stub
		
	}

}
