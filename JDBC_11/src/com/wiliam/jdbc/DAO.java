package com.wiliam.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class DAO {
	// INSERT,UPDATE，DELETE 操作都可以包含在其中

	public void update(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseDB(null, preparedStatement, connection);
		}
	}

	// 查询一条记录 返回对应的对象
	public <T> T get(Class<T> clazz, String sql, Object... args) {
		T entity = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// 1. 获取 Connection
			connection = JDBCTools.getConnection();
			// 2. 获取 preparedStatement
			preparedStatement = connection.prepareStatement(sql);
			// 3. 填充占位符
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			// 4. 进行查询，得到ResultSet
			resultSet = preparedStatement.executeQuery();
			// 5. 若 ResultSet 中有记录
			// 准备一个 Map<String,Object> ：键：存放列的别名， 值：存放列的值
			if (resultSet.next()) {

				Map<String, Object> values = new HashMap<String, Object>();

				// 6. 得到 ResultSetMetaData 对象
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				// 7. 处理 ResultSet 对象，把指针向下移动一个单位

				// 8. 由 ResultSetMetaData 对象得到结果集中有多少列
				int columnCount = resultSetMetaData.getColumnCount();

				// 9. 由 ResultSetMetaData 得到每一列的别名，由 ResultSet的到具体每一列的值
				for (int i = 0; i < columnCount; i++) {
					String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
					Object columnValue = resultSet.getObject(i + 1);
					// 10. 填充 Map 对象
					values.put(columnLabel, columnValue);
				}

				// 11. 用反射创建 Class 对应的对象
				entity = clazz.newInstance();
				// 12. 遍历 Map 对象，用反射填充对象的属性值：
				// 属性名为 Map 中的Key，属性值为 Map 中的 Value
				for (Map.Entry<String, Object> entry : values.entrySet()) {
					String propertyName = entry.getKey();
					Object value = entry.getValue();

//					ReflectionUtils.setFieldValue(entity, propertyName, value);
					BeanUtils.setProperty(entity, propertyName, value);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseDB(resultSet, preparedStatement, connection);
		}

		return entity;

	}

	// 查询多条记录，返回对应的对象的集合
	public <T> List<T> getForList(Class<T> clazz, String sql, Object... orgs) {

		return null;
	}

	// 返回某条记录的某一个字段的值 或 一个统计的值(一共有多少条记录等)
	public <E> E getForValue(String sql, Object... args) {
		return null;

	}
}
