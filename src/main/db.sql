-- 数据库
create database if not exists course_system;
use course_system;

-- 学员表
create table if not exists student
(
    id          bigint primary key auto_increment not null comment '主键ID',
    username    varchar(50) not null unique comment '登录用户名',
    password    varchar(100) not null comment '密码（加密存储）',
    name        varchar(50) not null comment '真实姓名',
    gender      varchar(2) not null comment '性别',
    phone       varchar(20) not null unique comment '手机号',
    email       varchar(100) default null comment '邮箱',
    province    varchar(32) not null comment '所在省份',
    create_time datetime not null default current_timestamp comment '注册时间',
    update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    status      tinyint not null default 1 comment '状态（1=正常，0=禁用）'
    ) comment '学员表';

-- 管理员表
create table if not exists admin
(
    id          bigint primary key auto_increment not null comment '主键ID',
    username    varchar(50) not null unique comment '管理员用户名',
    password    varchar(100) not null comment '密码（加密存储）',
    name        varchar(50) not null comment '真实姓名',
    phone       varchar(20) default null comment '手机号',
    email       varchar(100) default null comment '邮箱',
    ) comment '管理员表';

-- 课程表
create table if not exists course
(
    id          bigint primary key auto_increment not null comment '主键ID',
    name        varchar(100) not null comment '课程名称',
    description text comment '课程描述',
    price       decimal(10,2) not null default 0 comment '课程价格',
    duration    int not null comment '课程时长（分钟）',
    level       varchar(20) not null comment '课程难度（初级/中级/高级）',
    create_time datetime not null default current_timestamp comment '创建时间',
    update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    status      tinyint not null default 1 comment '状态（1=上架，0=下架）'
    ) comment '课程表';

-- 订单表
create table if not exists orders
(
    id          bigint primary key auto_increment not null comment '主键ID',
    student_id  bigint not null comment '学员ID',
    course_id   bigint not null comment '课程ID',
    order_no    varchar(100) not null unique comment '订单编号',
    amount      decimal(10,2) not null comment '支付金额',
    create_time datetime not null default current_timestamp comment '下单时间',
    update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    foreign key (student_id) references student(id),
    foreign key (course_id) references course(id)
    ) comment '订单表';

-- 保留原有预约表
create table if not exists reservation
(
    id                 bigint  primary key auto_increment   not null comment '主键ID',
    name               varchar(50) not null comment '学员姓名',
    gender             varchar(2)  not null comment '学员性别',
    phone              varchar(20) not null comment '学员手机号',
    communication_time datetime    not null comment '沟通时间',
    province           varchar(32) not null comment '学员所处的省份',
    estimated_score    int         not null comment '学员需求'
    ) comment '预约表';
