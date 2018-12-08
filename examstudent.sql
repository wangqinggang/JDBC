/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/6 23:48:08                           */
/*==============================================================*/


drop table if exists examstudent;

/*==============================================================*/
/* Table: examstudent                                           */
/*==============================================================*/
create table examstudent
(
   FlowID               int(11) not null,
   Type                 int(11),
   IDCard               varchar(18),
   ExamCard             varchar(15),
   StudentName          varchar(20),
   Location             varchar(20),
   Grade                int(11),
   primary key (FlowID)
);


INSERT INTO examstudent(FlowID,Type,IDCard,ExamCard,StudentName,Location,Grade)
values(10,4,'1234123','1234125124','william','山东',100)
