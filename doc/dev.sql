
#<ebook表>#####################################################################
drop table if exists `ebook`;
create table `ebook` (
  `id` bigint not null auto_increment comment 'id',
  `name` varchar(50) comment '名称',
  `category1_id` bigint comment '分类1',
  `category2_id` bigint comment '分类2',
  `description` varchar(200) comment '描述',
  `cover` varchar(200) comment '封面',
  `doc_count` int comment '文档数',
  `view_count` int comment '阅读数',
  `vote_count` int comment '点赞数',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='电子书';

insert into `ebook` (id, name, cover, description) values (1, 'Spring Boot 入门教程', '/image/cover1.png', '是时候学习 Spring Boot 了！');
insert into `ebook` (id, name, cover, description) values (2, 'Vue 入门教程', '/image/cover2.png', 'Vue 学到什么程度了啊？');
insert into `ebook` (id, name, cover, description) values (3, 'Python 入门教程', '/image/cover2.png', '人工智能时代，你还不懂 Python 吗？');
insert into `ebook` (id, name, cover, description) values (4, 'MySQL 入门教程', '/image/cover1.png', '数据库当然用 MySQL 啦！');
insert into `ebook` (id, name, cover, description) values (5, 'Java 入门教程', '/image/cover1.png', 'Java 感觉也没有那么难嘛！');



#<test表>#######################################################################
drop table if exists `test`;
create table `test` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';

insert into `test` (id, name, password) values(1, '测试', '4869');



#<demo表>#######################################################################
drop table if exists `demo`;
create table `demo` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';

insert into `demo` (id, name) values(1, '测试');




