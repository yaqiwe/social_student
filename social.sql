create DATABASE social_base

use social_base
CREATE TABLE label(
id VARCHAR(20) Not NULL COMMENT '标签ID',
label_name VARCHAR(100) NULL COMMENT '标签名称',
state VARCHAR(1) NULL COMMENT '状态',
count BIGINT(20) NULL COMMENT '使用数量',
recommend VARCHAR(1) NULL COMMENT '是否推荐',
fans BIGINT(20) NULL COMMENT '粉丝数',
PRIMARY KEY (id)
)


create DATABASE social_recruit

use social_recruit

CREATE TABLE enterprise(
id VARCHAR(20) NOT NULL COMMENT '企业ID',
name VARCHAR(100) NOT NULL COMMENT '企业名称',
summary VARCHAR(300) NULL COMMENT '企业简介',
address VARCHAR(300) NULL COMMENT '企业地址',
labels VARCHAR(100) NULL COMMENT '标签列表,用逗号,分隔',
coordinate VARCHAR(100) NULL COMMENT '企业位置坐标,经度,纬度',
ishot VARCHAR(1) NULL COMMENT '是否热门0表示非热门1表示热门',
logo VARCHAR(200) NULL COMMENT 'LOGO',
jobcount BIGINT(20) NULL COMMENT '职位数',
url VARCHAR(200) NULL COMMENT 'URL',
PRIMARY Key (id)
)COMMENT '企业表';


CREATE TABLE recruit(
id VARCHAR(20) NOT NULL COMMENT '招聘信息ID',
job_name VARCHAR(100) NOT NULL COMMENT '招聘职位',
salary VARCHAR(100) NOT NULL COMMENT '薪资范围',
conditions VARCHAR(500) NOT NULL COMMENT '经验要求',
education VARCHAR(100) NOT NULL COMMENT '学历要求',
type VARCHAR(500) NOT NULL COMMENT '任职方式',
address VARCHAR(300) NULL COMMENT '办公地址',
eid VARCHAR(20) NOT NULL COMMENT '企业ID',
create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP  COMMENT '发布日期',
state VARCHAR(1) NULL COMMENT '状态,0:关闭 1:开启 2:推荐',
url VARCHAR(200) NULL COMMENT '原网址',
label VARCHAR(100) NULL COMMENT '标签',
content1 VARCHAR(300) NULL COMMENT '职位描述',
content2 VARCHAR(300) NULL COMMENT '职位要求',
PRIMARY Key (id)
)COMMENT '招聘信息表';

//-------------------------------------------------------

create DATABASE social_qa

use social_qa


CREATE TABLE rpoblem(
id VARCHAR(20) NOT NULL COMMENT '问题Id',
title VARCHAR(100) NOT NULL COMMENT '问题标题',
content VARCHAR(500) NOT NULL COMMENT '问题内容',
create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布日期',
update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
user_id VARCHAR(20) NOT NULL COMMENT '问题发布人ID',
nick_name VARCHAR(20) NOT NULL COMMENT '发布人昵称',
visits BIGINT DEFAULT 0 COMMENT '浏览量',
thumb_up BIGINT DEFAULT 0 COMMENT '点赞数',
reply BIGINT DEFAULT 0 COMMENT '回复数',
solve VARCHAR(1) NULL COMMENT '是否解决',
reply_name VARCHAR(20) NULL COMMENT '最新回复人',
reply_time TIMESTAMP NULL COMMENT '最新回复时间',
PRIMARY KEY (id)
)COMMENT '问题表'

