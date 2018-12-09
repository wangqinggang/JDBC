package com.wiliam.jdbc;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class DAOTest {
	DAO dao=new DAO();

	@Test
	public void testUpdate() {
		String sql="INSERT INTO customers(name,"+
					"email,birth) VALUES(?,?,?)";
		dao.update(sql, "XiaoMing","xiaoming@atguigu.com",
				new Date(new java.util.Date().getTime()));
		
	}

	@Test
	public void testGet() {
		String sql="SELECT flow_id flowId,type,exam_card examCard,"+
					"id_card idCard,student_name studentName,location,"+
					"grade From examstudent WHERE flow_id = ?";
		
		Student student=dao.get(Student.class,sql,1);
		System.out.println(student);
	}

	@Test
	public void testGetForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForValue() {
		fail("Not yet implemented");
	}

}
