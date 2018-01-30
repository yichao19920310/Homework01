--drop sequence 
DROP SEQUENCE SEQ_USER;
DROP SEQUENCE SEQ_COMMENT;
DROP SEQUENCE SEQ_NEWS INCREMENT;
DROP SEQUENCE SEQ_ORDER INCREMENT;
DROP SEQUENCE SEQ_PRODUCT_CATEGORY;
DROP SEQUENCE SEQ_PRODUCT INCREMENT;
DROP SEQUENCE SEQ_DETAIL INCREMENT;
DROP SEQUENCE SEQ_HWUA_CART;

--drop tables
DROP TABLE HWUA_CART;
DROP table HWUA_ORDER_DETAIL;
DROP table HWUA_PRODUCT;
DROP table HWUA_PRODUCT_CATEGORY;
DROP table HWUA_ORDER;
DROP table HWUA_NEWS;
DROP table HWUA_COMMENT;
DROP table HWUA_USER;
commit;

--create user myAmazon identified by 1234;
--grant connect,resource to myAmazon;
--conn myAmazon/1234;

--create sequence
CREATE SEQUENCE SEQ_USER INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE SEQ_COMMENT INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_NEWS INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_ORDER INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_PRODUCT_CATEGORY INCREMENT BY 100 START WITH 1;
CREATE SEQUENCE SEQ_PRODUCT INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_DETAIL INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_HWUA_CART INCREMENT BY 1 START WITH 1;

--create tables
create table HWUA_USER
(
  HU_USER_ID       NUMBER(10)  PRIMARY KEY,--�û�id
  HU_USER_NAME     VARCHAR2(20) not null,
  HU_PASSWORD      VARCHAR2(20) not null,
  HU_SEX           CHAR(2) not null,
  HU_BIRTHDAY      DATE,
  HU_IDENTITY_CODE VARCHAR2(60),
  HU_EMAIL         VARCHAR2(80),
  HU_MOBILE        VARCHAR2(11),
  HU_ADDRESS       VARCHAR2(200) not null,
  HU_STATUS        NUMBER(6) not null
)
/

create table HWUA_COMMENT
(
  HC_ID          NUMBER(10) PRIMARY KEY,--����id
  HC_REPLY       VARCHAR2(200),
  HC_CONTENT     VARCHAR2(200) not null,
  HC_CREATE_TIME DATE not null,
  HC_REPLY_TIME  DATE,
  HC_NICK_NAME   VARCHAR2(50) not null,
  HC_STATE       VARCHAR2(20)
)
/
create table HWUA_NEWS
(
  HN_ID          NUMBER(10)  PRIMARY KEY,--����id
  HN_TITLE       VARCHAR2(80) not null,
  HN_CONTENT     VARCHAR2(1000) not null,
  HN_CREATE_TIME DATE not null
)
/
create table HWUA_ORDER
(
  HO_ID           NUMBER(10) PRIMARY KEY,--����id
  HO_USER_ID      NUMBER(10),
  HO_USER_NAME    VARCHAR2(50) not null,
  HO_USER_ADDRESS VARCHAR2(200) not null,
  HO_CREATE_TIME  DATE not null,
  HO_COST         NUMBER(10,2) not null,
  HO_STATUS       NUMBER(6) not null,
  HO_TYPE         NUMBER(6) not null,
  constraint HO_USER_ID_FK foreign key(HO_USER_ID) references HWUA_USER (HU_USER_ID)
)
/
create table HWUA_PRODUCT_CATEGORY
(
  HPC_ID        NUMBER(10) PRIMARY KEY,--��Ʒ���id
  HPC_NAME      VARCHAR2(50) not null,
  HPC_PARENT_ID NUMBER(10) not null
)
/
create table HWUA_PRODUCT
(
  HP_ID          NUMBER(10)   PRIMARY KEY,--��Ʒid
  HP_NAME        VARCHAR2(50) not null,
  HP_DESCRIPTION VARCHAR2(100),
  HP_PRICE       NUMBER(10,2) not null,
  HP_STOCK       NUMBER(10) not null,
  HPC_ID         NUMBER(10) ,
  HPC_CHILD_ID   NUMBER(10) ,
  HP_FILE_NAME   VARCHAR2(200) not null,
  constraint HPC_ID_FK foreign key (HPC_ID) references HWUA_PRODUCT_CATEGORY (HPC_ID),
  constraint HPC_CHILD_ID foreign key (HPC_CHILD_ID) references HWUA_PRODUCT_CATEGORY (HPC_ID)
)
/
create table HWUA_ORDER_DETAIL
(
  HOD_ID       NUMBER(10) PRIMARY KEY,--订单详情
  HO_ID        NUMBER(10),
  HP_ID        NUMBER(10),
  HOD_QUANTITY NUMBER(10) not null,
  HOD_COST     NUMBER(10,2) not null,
  constraint HOD_ID_FK foreign key (HO_ID) references HWUA_ORDER(HO_ID),
  constraint HOD_HP_ID_FK foreign key (HP_ID) references HWUA_PRODUCT(HP_ID)
)
/

CREATE TABLE HWUA_CART 
   (ID NUMBER(10) PRIMARY KEY, --购物车id
	PID NUMBER(10), 
	QUANTITY NUMBER(10), 
	USERID NUMBER(10)
   )
/
insert into HWUA_USER (HU_USER_ID, HU_USER_NAME, HU_PASSWORD, HU_SEX, HU_BIRTHDAY, 
HU_IDENTITY_CODE, HU_EMAIL, HU_MOBILE, HU_ADDRESS, HU_STATUS)
values (SEQ_USER.NEXTVAL, '朱琦', '1234', '0 ', to_date('02-07-1989', 'dd-mm-yyyy'), 
'500235198907026299', 'sa@sina.com', '13011092066', '重庆', 1);
insert into HWUA_USER (HU_USER_ID, HU_USER_NAME, HU_PASSWORD, HU_SEX, HU_BIRTHDAY, 
HU_IDENTITY_CODE, HU_EMAIL, HU_MOBILE, HU_ADDRESS, HU_STATUS)
values (SEQ_USER.NEXTVAL, '朱琅', '1234', '1 ', to_date('02-07-1988', 'dd-mm-yyyy'), 
'500235198907026299', 'sa@sina.com', '13011092066', '重庆', 1);
insert into HWUA_USER (HU_USER_ID, HU_USER_NAME, HU_PASSWORD, HU_SEX, HU_BIRTHDAY, 
HU_IDENTITY_CODE, HU_EMAIL, HU_MOBILE, HU_ADDRESS, HU_STATUS)
values (SEQ_USER.NEXTVAL, '刁星辰', '1234', '1 ', to_date('02-09-1990', 'dd-mm-yyyy'),
'123123123123123', 'web@sohu.com', '12312312312', '东北', 1);
insert into HWUA_USER (HU_USER_ID, HU_USER_NAME, HU_PASSWORD, HU_SEX, HU_BIRTHDAY, 
HU_IDENTITY_CODE, HU_EMAIL, HU_MOBILE, HU_ADDRESS, HU_STATUS)
values (SEQ_USER.NEXTVAL, '刘博', '1234', '0 ', to_date('02-07-1988', 'dd-mm-yyyy'), 
'500235198907026299', 'sa@sina.com', '13011092066', '北京', 1);
insert into HWUA_USER (HU_USER_ID, HU_USER_NAME, HU_PASSWORD, HU_SEX, HU_BIRTHDAY, 
HU_IDENTITY_CODE, HU_EMAIL, HU_MOBILE, HU_ADDRESS, HU_STATUS)
values (SEQ_USER.NEXTVAL, 'admin', '1234', '0 ', to_date('02-07-1988', 'dd-mm-yyyy'), 
'12345678912345612X', 'sa@sina.com', '13011092066', '上海', 1);


insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '最新酷睿笔记本', 'i7超强配置，赶快下手啦!', to_date('08-03-2012 20:56:57',
'dd-mm-yyyy hh24:mi:ss'), to_date('08-03-2012 20:56:57', 'dd-mm-yyyy hh24:mi:ss'), '天天', null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '团购无忧', '团购上拉手拉手上团购', to_date('08-03-2012 20:56:57',
'dd-mm-yyyy hh24:mi:ss'), to_date('08-03-2012 20:56:57', 'dd-mm-yyyy hh24:mi:ss'), '拉手', null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '会员特惠月开始', '积分大返利，机不可失失不再来!', to_date('08-03-2012 20:56:57', 
'dd-mm-yyyy hh24:mi:ss'), to_date('08-03-2012 20:56:57', 'dd-mm-yyyy hh24:mi:ss'), '会员', 
null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '加入会员，赢千万大礼包', '我是会员我快乐啦啦啦啦啦', to_date('09-03-2012 16:49:14', 
'dd-mm-yyyy hh24:mi:ss'), to_date('09-03-2012 16:49:14', 'dd-mm-yyyy hh24:mi:ss'), '我是神', 
null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '赢双旦促销大酬宾', '元旦圣诞超级大礼包等你拿啦', to_date('09-03-2012 16:50:17', 'dd-
mm-yyyy hh24:mi:ss'), to_date('09-03-2012 16:50:17', 'dd-mm-yyyy hh24:mi:ss'), '双蛋', null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '新年不夜夏，通宵也是开张啦', '新年不夜夏，通宵也是开张啦' || chr(10) || '新年不夜夏，
通宵也是开张啦' || chr(10) || '', to_date('09-03-2012 16:53:11', 'dd-mm-yyyy hh24:mi:ss'), 
to_date('09-03-2012 16:53:11', 'dd-mm-yyyy hh24:mi:ss'), '新年', null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '积分兑换开始了', '积分兑换开始了积分兑换开始了积分兑换开始了', to_date('09-03-2012 
16:53:11', 'dd-mm-yyyy hh24:mi:ss'), to_date('09-03-2012 16:55:50', 'dd-mm-yyyy hh24:mi:ss'), '
兑换', null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '配货通知', '开始配货开始配货开始配货开始配货!', to_date('10-03-2012 16:54:11', 'dd-
mm-yyyy hh24:mi:ss'), to_date('10-03-2012 16:56:50', 'dd-mm-yyyy hh24:mi:ss'), '配货', '配送在途
中');
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '团购阿迪1折起', '团购阿迪1折起团购阿迪1折起团购阿迪1折起！', to_date('10-03-2012 
16:54:11', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-03-2012 16:56:50', 'dd-mm-yyyy hh24:mi:ss'), '
团购', null);
insert into HWUA_COMMENT (HC_ID,HC_REPLY, HC_CONTENT, HC_CREATE_TIME, HC_REPLY_TIME, 
HC_NICK_NAME, HC_STATE)
values (SEQ_COMMENT.NEXTVAL, '方便', '阿什顿', to_date('21-03-2012 09:22:04', 'dd-mm-yyyy hh24:mi:ss'), to_date
('21-03-2012 09:22:04', 'dd-mm-yyyy hh24:mi:ss'), '飞飞', null);


insert into HWUA_NEWS (HN_ID, HN_TITLE, HN_CONTENT, HN_CREATE_TIME)
values (SEQ_NEWS.nextval, '会员特惠月开始', '积分大返利，机不可失失不再来!', to_date('08-03-2012 20:56:52', 
'dd-mm-yyyy hh24:mi:ss'));
insert into HWUA_NEWS (HN_ID, HN_TITLE, HN_CONTENT, HN_CREATE_TIME)
values (SEQ_NEWS.nextval, '加入会员，赢千万大礼包', '我是会员我快乐啦啦啦啦啦', to_date('08-03-2012 20:56:52', 
'dd-mm-yyyy hh24:mi:ss'));
insert into HWUA_NEWS (HN_ID, HN_TITLE, HN_CONTENT, HN_CREATE_TIME)
values (SEQ_NEWS.nextval, '积分兑换开始了', '积分兑换开始了积分兑换开始了积分兑换开始了', to_date('08-03-2012 
20:56:52', 'dd-mm-yyyy hh24:mi:ss'));
insert into HWUA_NEWS (HN_ID, HN_TITLE, HN_CONTENT, HN_CREATE_TIME)
values (SEQ_NEWS.nextval, '配货通知', '开始配货开始配货开始配货开始配货!', to_date('08-03-2012 20:56:52', 'dd-
mm-yyyy hh24:mi:ss'));
insert into HWUA_NEWS (HN_ID, HN_TITLE, HN_CONTENT, HN_CREATE_TIME)
values (SEQ_NEWS.nextval, '团购阿迪1折起', '团购阿迪1折起团购阿迪1折起团购阿迪1折起！', to_date('08-03-2012 
20:56:52', 'dd-mm-yyyy hh24:mi:ss'));
insert into HWUA_NEWS (HN_ID, HN_TITLE, HN_CONTENT, HN_CREATE_TIME)
values (SEQ_NEWS.nextval, '汇源果汁大甩卖', '甩卖甩卖甩卖甩卖甩卖甩卖', to_date('08-03-2012 20:56:52', 'dd-mm-
yyyy hh24:mi:ss'));
insert into HWUA_NEWS (HN_ID, HN_TITLE, HN_CONTENT, HN_CREATE_TIME)
values (SEQ_NEWS.nextval, '苹果手机不要钱', '不要不要钱不要钱不要钱不要钱', to_date('08-03-2012 20:56:52', 
'dd-mm-yyyy hh24:mi:ss'));


insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:52', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1001', '朱琦', '重庆', to_date('20-03-2012 23:25:31', 'dd-mm-yyyy hh24:mi:ss'), 
22191, 1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:53', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:54', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:55', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:55', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:56', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:57', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:58', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);
insert into HWUA_ORDER (HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, 
HO_COST, HO_STATUS, HO_TYPE)
values (SEQ_ORDER.NEXTVAL, '1000', '234', '123', to_date('19-03-2012 11:18:59', 'dd-mm-yyyy hh24:mi:ss'), 100, 
1, 1);


insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (19, '服饰', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (21, '品牌', 21);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (22, '团购', 22);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (1, '图音', 1);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (2, '百货', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (28, 'qq', 1);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (7, '家居', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (8, '美妆', 22);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (9, '母婴', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (10, '食品', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (11, '手机数码', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (12, '家居首饰', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (13, '手表饰品', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (14, '鞋包', 21);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (15, '家电', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (16, '电脑办公', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (17, '玩具文具', 21);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (18, '汽车用品', 21);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (20, '彩票充值', 2);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (4, '音乐', 1);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (5, '运动健康', 0);
insert into HWUA_PRODUCT_CATEGORY (HPC_ID, HPC_NAME, HPC_PARENT_ID)
values (23, '易买社区', 23);


insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '乐扣普通型保鲜盒圣诞7件套', '圣诞7件套', 69, 2000, 2, 7, 'images/product/2.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '欧珀莱均衡保湿四件套', '均衡保湿四件套', 279, 50, 2, 8, 'images/product/3.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '联想笔记本电脑 高速独立显存', '高速独立显存 I7处理器', 4199, 50, 2, 16, 
'images/product/4.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '德菲丝巧克力', '108.00', 100, 111, 1, 4, 'images/product/1.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, 'Genius925纯银施华洛世奇水晶吊坠', '华洛世奇水晶吊坠', 69, 50, 2, 12, 
'images/product/6.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '利仁2018M福满堂电饼铛 好用实惠', '福满堂电饼铛 好用实惠', 268, 50, 2, 15, 
'images/product/7.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '达派高档拉杆箱20寸 经典款式', '高档拉杆箱20寸 经典款式', 198, 50, 2, 14, 
'images/product/8.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '爱国者MP4 全屏触摸多格式播放 4G', '全屏触摸多格式播放 4G', 289, 50, 2, 11, 
'images/product/0.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '多美滋金装金盾3阶段幼儿配方奶粉', '金盾3阶段幼儿配方奶粉', 186, 50, 1, 10, 
'images/product/10.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '德菲丝巧克力', '巧克力500g/盒', 108, 100, 1, 10, 'images/product/1.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '乐扣普通型保鲜盒圣诞7件套', '圣诞7件套', 69, 2000, 2, 7, 'images/product/2.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '欧珀莱均衡保湿四件套', '均衡保湿四件套', 279, 50, 2, 8, 'images/product/3.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '联想笔记本电脑 高速独立显存', '高速独立显存 I7处理器', 4199, 50, 2, 16, 
'images/product/4.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '红色上衣', 199, 100, 2, 19, 'images/product/clothes2.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, 'Genius925纯银施华洛世奇水晶吊坠', '华洛世奇水晶吊坠', 69, 50, 2, 12, 
'images/product/6.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '利仁2018M福满堂电饼铛 好用实惠', '福满堂电饼铛 好用实惠', 268, 50, 2, 15, 
'images/product/7.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '达派高档拉杆箱20寸 经典款式', '高档拉杆箱20寸 经典款式', 198, 50, 2, 14, 
'images/product/8.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '爱国者MP4 全屏触摸多格式播放 4G', '全屏触摸多格式播放 4G', 289, 50, 2, 11, 
'images/product/0.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '多美滋金装金盾3阶段幼儿配方奶粉', '金盾3阶段幼儿配方奶粉', 186, 50, 1, 10, 
'images/product/10.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '夹克', 99, 100, 2, 19, 'images/product/clothes3.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '粉色上衣', 96, 100, 2, 19, 'images/product/clothes5.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '灰色上衣', 299, 100, 2, 19, 'images/product/clothes6.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '红色上衣', 199, 100, 2, 19, 'images/product/clothes7.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '银色上衣', 599, 100, 2, 19, 'images/product/clothes8.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '蓝色上衣', 399, 100, 2, 19, 'images/product/clothes9.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '浅蓝色上衣', 499, 100, 2, 19, 'images/product/clothes10.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '上衣', '白色上衣', 68, 100, 2, 19, 'images/product/clothes4.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '黑色皮鞋', 299, 100, 21, 14, 'images/product/shoes1.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '灰色皮鞋', 299, 100, 21, 14, 'images/product/shoes2.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '蓝色皮鞋', 299, 100, 21, 14, 'images/product/shoes3.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '灰色皮鞋', 299, 100, 21, 14, 'images/product/shoes4.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '棕色皮鞋', 299, 100, 21, 14, 'images/product/shoes5.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '黑色皮鞋', 299, 100, 21, 14, 'images/product/shoes6.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '黑色皮鞋', 299, 100, 21, 14, 'images/product/shoes7.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '黑色皮鞋', 299, 100, 21, 14, 'images/product/shoes8.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '黑色皮鞋', 299, 100, 21, 14, 'images/product/shoes9.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '皮鞋', '黑色皮鞋', 299, 100, 21, 14, 'images/product/shoes10.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass1.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass2.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass3.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass4.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass5.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass6.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass7.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass8.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass9.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '眼镜', '太阳镜', 299, 100, 2, 12, 'images/product/sunglass10.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 1299, 100, 2, 15, 'images/product/television1.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 1299, 100, 2, 15, 'images/product/television2.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 2299, 100, 2, 15, 'images/products/television3.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 2299, 100, 2, 15, 'images/product/television4.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 3299, 100, 2, 15, 'images/product/television5.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 2299, 100, 2, 15, 'images/product/television6.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 4299, 100, 2, 15, 'images/product/television7.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 5299, 100, 2, 15, 'images/product/television8.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 2299, 100, 2, 15, 'images/product/television9.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '电视', '彩电', 6299, 100, 2, 15, 'images/product/television10.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '卡地亚', '见证你的爱情', 22122, 100, 2, 13, 'images/product/20.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '卡地亚', '见证你的爱情', 22122, 100, 22, 22, 'images/product/20.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '双色球', '来吧', 2, 1000, 2, 20, 'images/product/21.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '变形金刚', '大黄蜂', 222, 1000, 21, 17, 'images/product/23.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, 'GPS', '导航仪', 2222, 1000, 21, 18, 'images/product/24.jpg');
insert into HWUA_PRODUCT (HP_ID, HP_NAME, HP_DESCRIPTION, HP_PRICE, HP_STOCK, HPC_ID, 
HPC_CHILD_ID, HP_FILE_NAME)
values (SEQ_PRODUCT.NEXTVAL, '123', null, 100, 111, 2, 9, 'images\product\133214492473826.jpg');

--transaction
commit;