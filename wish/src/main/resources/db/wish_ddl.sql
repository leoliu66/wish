CREATE TABLE `wx_finance` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `order_id` varchar(32) NOT NULL COMMENT '订单编号 同一笔单修改多少次订单id一样',
  `new_record` varchar(1) DEFAULT 'Y' COMMENT '是否最新记录: Y是 N否',
  `principal` varchar(32) NOT NULL COMMENT '负责人',
  `principal_phone` varchar(32) DEFAULT '' COMMENT '负责人手机号',
  `money` decimal(16,2) DEFAULT NULL COMMENT '金额',
  `money_type` varchar(16) NOT NULL DEFAULT 'income' COMMENT '类型: income(收入),expend （支出）',
  `resource` varchar(32) NOT NULL COMMENT '金额去向或者来源',
  `images` varchar(255) DEFAULT '' COMMENT '截图地址',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_principal` (`principal`),
  KEY `idx_create_date` (`create_date`),
  KEY `idx_create_by` (`create_by`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='微细财务表';

CREATE TABLE `wx_order` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `order_id` varchar(32) NOT NULL COMMENT '订单编号 同一笔单修改多少次订单id一样',
  `new_record` varchar(1) DEFAULT 'Y' COMMENT '是否最新记录: Y是 N否',
  `contacts` varchar(32) DEFAULT '' COMMENT '联系人',
  `contacts_phone` varchar(32) DEFAULT '' COMMENT '联系人手机号',
  `salesman` varchar(32) DEFAULT '' COMMENT '开单人',
  `salesman_phone` varchar(32) DEFAULT '' COMMENT '开单人手机号',
  `business` varchar(32) DEFAULT '' COMMENT '业务员',
  `business_phone` varchar(32) DEFAULT '' COMMENT '业务员手机号',
  `developer` varchar(32) DEFAULT '' COMMENT '开发员',
  `developer_phone` varchar(32) DEFAULT '' COMMENT '开发员手机号',
  `order_status` varchar(16) DEFAULT 'nocheck' COMMENT '审核状态:success 已完成 developing 开发中 check 已审核 nocheck 未审核',
  `pay_status` varchar(16) DEFAULT 'nonpayment' COMMENT '支付状态:success 已支付 downpayment 定金 nonpayment 未支付 refunded 已退款 ',
  `cost` decimal(16,2) DEFAULT NULL COMMENT '成本',
  `down_payment` decimal(16,2) DEFAULT NULL COMMENT '定金',
  `final_payment` decimal(16,2) DEFAULT NULL COMMENT '尾款',
  `money` decimal(16,2) DEFAULT NULL COMMENT '订单金额 定金+尾款',
  `discount_amount` decimal(16,2) DEFAULT NULL COMMENT '优惠金额',
  `all_money` decimal(16,2) DEFAULT NULL COMMENT '订单总金额 订单金额+优惠金额',
  `texture` varchar(32) DEFAULT '' COMMENT '材质',
  `print` varchar(1) DEFAULT 'N' COMMENT '是否需要打印: Y是 N否',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_contacts` (`contacts`),
  KEY `idx_contacts_phone` (`contacts_phone`),
  KEY `idx_create_date` (`create_date`),
  KEY `idx_create_by` (`create_by`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='微细订单表';






