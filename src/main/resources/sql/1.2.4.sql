CREATE TABLE `expect_date_query` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expect_date` datetime NOT NULL COMMENT '出发日期',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `creator` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '创建人',
  `updater` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`)
);



CREATE TABLE `order_expect_date_query` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expect_date_query_id` int(11) NOT NULL COMMENT '出发日期表id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `creator` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '创建人',
  `updater` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '修改人',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '关联状态，0删除，1正常',
  PRIMARY KEY (`id`)
);

