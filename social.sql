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


CREATE TABLE problem(
id VARCHAR(20) NOT NULL COMMENT '问题Id',
title VARCHAR(100) NOT NULL COMMENT '问题标题',
content VARCHAR(500) NOT NULL COMMENT '问题内容',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布日期',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
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

CREATE TABLE pl(
problem_id VARCHAR(20) NOT NULL COMMENT '问题ID',
labl_id VARCHAR(20) NOT NULL COMMENT '标签ID',
PRIMARY KEY(problem_id,labl_id)
)COMMENT '问题，标签关系表'

CREATE TABLE reply(
id VARCHAR(20) NOT NULL COMMENT 'id',
problem_id VARCHAR(20) NOT NULL COMMENT '问题ID',
content VARCHAR (500) NOT NULL COMMENT '回复的内容',
create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
user_id VARCHAR(20) NOT NULL COMMENT '回复的人的ID',
nick_name VARCHAR(20) NOT NULL COMMENT '回复人的昵称',
PRIMARY KEY (id)
)COMMENT '回复表'

CREATE DATABASE social_article

use social_article

CREATE TABLE article(
	id VARCHAR(20) not NULL COMMENT 'id',
	column_id VARCHAR(20) not NULL COMMENT '专栏ID',
	user_id VARCHAR (20) not NULL COMMENT '用户ID',
	title VARCHAR(100) not NULL COMMENT '文章标题',
	content VARCHAR(2000) not NULL COMMENT '文章内容',
	iamge VARCHAR(300) not null COMMENT '文章封面',
	create_time TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
	update_time TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	is_public VARCHAR(1) not NULL DEFAULT 1 COMMENT '是否公开0:不公开,1:公开',
	is_top VARCHAR(1) NOT NULL DEFAULT 0 COMMENT '是否置顶0:不置顶,1:置顶',
	visits BIGINT NOT NULL  COMMENT '浏览量',
	thumb_up BIGINT NOT NULL COMMENT '点赞数',
	comment BIGINT NOT NULL COMMENT '评论数',
	state VARCHAR(1) NOT NULL DEFAULT 0 COMMENT '审核状态0:未审核,1:已审核',
	channel_id BIGINT NOT NULL COMMENT '所属频道ID',
	url VARCHAR(300) NULL COMMENT 'url地址',
	type VARCHAR(1) NOT NULL COMMENT '文章类型0:分享,1专栏',
	PRIMARY KEY (id)
)COMMENT '文章'

CREATE DATABASE social_user

use social_user

CREATE TABLE `admin` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `loginname` varchar(100) DEFAULT NULL COMMENT '登陆名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) COMMENT='管理员';

CREATE TABLE `follow` (
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `targetuser` varchar(20) NOT NULL COMMENT '被关注用户ID',
  PRIMARY KEY (`userid`,`targetuser`)
) ;


CREATE TABLE `user` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `birthday` TIMESTAMP DEFAULT NULL COMMENT '出生年月日',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT 'E-Mail',
  `regdate` TIMESTAMP DEFAULT NULL COMMENT '注册日期',
  `updatedate` TIMESTAMP DEFAULT NULL COMMENT '修改日期',
  `lastdate` TIMESTAMP DEFAULT NULL COMMENT '最后登陆日期',
  `online` bigint(20) DEFAULT NULL COMMENT '在线时长（分钟）',
  `interest` varchar(100) DEFAULT NULL COMMENT '兴趣',
  `personality` varchar(100) DEFAULT NULL COMMENT '个性',
  `fanscount` int(20) DEFAULT NULL COMMENT '粉丝数',
  `followcount` int(20) DEFAULT NULL COMMENT '关注数',
  PRIMARY KEY (`id`)
)COMMENT='用户';