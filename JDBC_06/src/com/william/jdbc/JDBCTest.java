package com.william.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

public class JDBCTest {
	@Test
	public void testGetStudent() {
		// 1. 得到查询的类型
		int searchType = getSearchTypeFromConsole();

		// 2. 具体查询学生信息
		Student student = searchStudent(searchType);

		// 3. 打印学生信息
		printStudent(student);

	}

	/**
	 * 从控制台读入一个整数，确定要查询的类型
	 * 
	 * @param student
	 *            1. 用身份证查询。 2. 用准考证查询，其他的无效， 并提示用户重新输入。
	 */
	private void printStudent(Student student) {
		if (student!=null) {
			System.out.println(student);
		}else {
			System.out.println("查无此人！");
		}
	}

	/**
	 * 具体查询学生信息的方法。返回一个Student 对象，若不存在，则返回 NULL
	 * 
	 * @param searchType：1 或 2
	 * @return
	 */
	private Student searchStudent(int searchType) {

		String  sql="SELECT flowid,type,idcard,examcard,"+
					"studentname,location,grade "+
					"FROM examstudent "+
					"WHERE ";
		
		Scanner scanner=new Scanner(System.in);
		
		// 1. 根据输入的 searchType ，提示用户输入信息
		// 1.1 若 searchType 为 1 ，提示：请输入身份证号。若为2，提示：请输入准考证号
		// 2. 根据 searchType 确定 SQL
		if (searchType == 1) {
			System.out.println("请输入身份证号：");
			String idcard=scanner.next();
			sql=sql+"idcard = '"+idcard +"'";
		}	else {
			System.out.println("请输入准考证号：");
			String examCard=scanner.next();
			sql=sql+"examCard = '"+examCard +"'";
		}
		

		// 3. 执行查询
		Student student=getStudent(sql);
		// 4. 若存在查询结果，把查询结果封装成为一个 Student 对象
		return student;
	}
	
	/**
	 * 根据传入的sql 返回Student 对象
	 * @param sql
	 * @return
	 */
	private Student getStudent(String sql) {
		Student student=null;
		
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		
		try {
			connection=JDBCTools.getConnection();
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			if (resultSet.next()) {
				student=new Student(
						resultSet.getInt(1), 
						resultSet.getInt(2), 
						resultSet.getString(3),
						resultSet.getString(4), 
						resultSet.getString(5), 
						resultSet.getString(6), 
						resultSet.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.releaseDB(resultSet, statement, connection);
		}
		return student;
		
	}

	/**
	 * 从控制台读入一个整数，确定要查询的类型
	 * 
	 * @return 1：用身份证查询 2. 用准考证查询, 其他的无效，并提示用户重新输入
	 */
	private int getSearchTypeFromConsole() {

		System.out.println("请输入查询类型：1.用身份证查询  2. 用准考证查询");
		Scanner scanner = new Scanner(System.in);

		int type = scanner.nextInt();

		if (type != 1 && type != 2) {
			System.out.println("输入有误！ 请重新输入!");
			throw new RuntimeException();
		}
		

		return type;
	}

//	@Test
//	public void testAddNewStudent() {
//		Student student = getStudentFromConsole();
//		addNewStudent(student);
//	}

	/**
	 * 从控制台输入学生信息
	 */
	private Student getStudentFromConsole() {

		Scanner scanner = new Scanner(System.in);
		Student student = new Student();

		System.out.print("FlowId：");
		student.setFlowId(scanner.nextInt());

		System.out.print("Type：");
		student.setType(scanner.nextInt());

		System.out.print("IdCard：");
		student.setIdCard(scanner.next());

		System.out.print("ExamCard：");
		student.setExamCard(scanner.next());

		System.out.print("StudentName：");
		student.setStudentName(scanner.next());

		System.out.print("Location：");
		student.setLocation(scanner.next());

		System.out.print("Grade：");
		student.setGrade(scanner.nextInt());

		return student;
	}

	public void addNewStudent(Student student) {
		// 1.准备一条SQL语句：
		String sql = "INSERT INTO examstudent " + "VALUES(" + student.getFlowId() + "," + student.getType() + ",'"
				+ student.getIdCard() + "','" + student.getExamCard() + "','" + student.getStudentName() + "','"
				+ student.getLocation() + "'," + student.getGrade() + ")";

		System.out.println(sql);
		// 2.调用 JDBCTools 类的 update(sql) 方法执行插入操作
		JDBCTools.update(sql);
	}

}
