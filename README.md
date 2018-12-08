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

