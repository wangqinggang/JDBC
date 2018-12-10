# JDBC
## 任务1： JDBC_通过Driver接口获取数据库连接
  * 项目文件名： `JDBC_01`
  * 说明：需要根据 `jdbc.properties` 文件建立相应的数据库
## 任务2： JDBC_通过DriverManager获取数据库连接
  * 项目文件名： `JDBC_02`
  * 说明：相应配置见 `JDBC_o1`
## 任务3： JDBC_通过Statement执行更新操作
  * 项目文件名： `JDBC_03`
  * 说明：
        * 需要新增 customer 数据库表 
        * 属性包括 id，name，email，birth 
        * 类型分别为 int(6),varchar(25),varchar(25),date
        * 其中 id 设置为自增长
## 任务4： JDBC_通过ResultSet执行查询操作
  * 项目文件名： `JDBC_04`
  * 说明：相应配置见 `JDBC_03`
## 任务5： JDBC_第一天复习
  * 项目文件名： `JDBC_05`
  * 说明：本案例项目总结任务1、2、3、4中内容
      主要包括：`Driver`   `DriverManager`   `Statement`  `Statement` 对象的使用

## 任务6： JDBC_以面向对象的方式编写JDBC程序
  * 项目文件名： `JDBC_06`
  * 说明：以面向对象方式查询学生信息，具体要求在PPT内（最外层ppt文件）
  *   `examstudent.sql` 文件为该项目所需数据库表

## 任务7： JDBC_PreparedStatement
  * 项目文件名： `JDBC_07`
  * 说明：PreparedStatement 方便SQL 插入 以及预防 SQL 注入
  * 需要新建 users 数据表，包含 `username varchar(25)` `password varchar(25)` 两个字段，自己随机插入数据，注意更改代码

## 任务8： JDBC_利用反射及JDBC元数据编写通用的查询方法
  * 项目文件名： `JDBC_07`
  * 说明：需要更改数据库表 examstudent 表，将列名改为 SQL 查询中相应的列名
  * 本项目 采用了Java 反射的相关技术，在学习中碰到如下问题：
  * 反射原理，类反射通用方法中，sql.date 与 util.data 类型转换问题待解决

## 任务9： JDBC_第二天小结
  * 说明：见任务10

## 任务10： JDBC_DAO设计模式
  * 项目文件名： `JDBC_10`
  * 说明：JDBC_DAO设计模式详解，并通过Junit 测试 调用数据库更新和查询一条记录

## 任务11： JDBC_使用BeanUtils工具类操作JavaBean
  * 项目文件名： `JDBC_11`
  * 说明：JDBC_使用BeanUtils工具类操作JavaBean，设置和获取JavaBean 的属性和方法 ，注意导入 `commons-beanutils-1.9.3.jar` 和  `commons-logging-1.2.jar`

## 任务12： JDBC_重构DAO查询方法&amp;完成DAO编写
  * 项目文件名： `JDBC_12`
  * 说明：JDBC_重构DAO查询方法&amp;完成DAO编写，包括更新记录，获取一条记录，获取多条记录，获取某一特定属性

## 任务13： JDBC_JDBC的元数据
  * 项目文件名： `JDBC_13`
  * 说明：ResultSetMetaData: 描述结果集中的元数据，
  * 可以得到结果集中的基本信息：结果集中有哪些列，列名，列的别名等。
	* 结合反射可以写出通用的查询方法。
  * DatabaseMetaData 是描述 数据库 的元数据对象
	* 可以由 Connection 得到