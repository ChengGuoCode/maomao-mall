/* 用户表 user */
CREATE
DATABASE maomao_mall_user;
CREATE TABLE maomao_mall_user.`mao_user`
(
    `id`          VARCHAR(64)  NOT NULL COMMENT '用户ID',
    `username`    VARCHAR(32)  NOT NULL COMMENT '用户名',
    `password`    VARCHAR(128) NOT NULL COMMENT '密码',
    `creator`     VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '用户表';
CREATE TABLE maomao_mall_user.`mao_user_role`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `uid`         VARCHAR(64) NOT NULL COMMENT '用户ID',
    `rid`         BIGINT      NOT NULL COMMENT '角色ID',
    `creator`     VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '用户角色表';
CREATE TABLE maomao_mall_user.`mao_role`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name`   VARCHAR(32) NOT NULL COMMENT '角色名称',
    `role_desc`   VARCHAR(32) NOT NULL COMMENT '角色描述',
    `creator`     VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '角色表';
CREATE TABLE maomao_mall_user.`mao_role_permission`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `rid`         BIGINT      NOT NULL COMMENT '角色ID',
    `pid`         BIGINT      NOT NULL COMMENT '权限ID',
    `creator`     VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '角色权限表';
CREATE TABLE maomao_mall_user.`mao_permission`
(
    `id`             BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `permission_key` VARCHAR(32) NOT NULL COMMENT '权限键',
    `url`            VARCHAR(256)         DEFAULT NULL COMMENT '访问路径',
    `parent_id`      BIGINT      NOT NULL DEFAULT '0' COMMENT '父级权限ID',
    `creator`        VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time`    datetime    NOT NULL COMMENT '创建时间',
    `updator`        VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time`    datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '权限表';

/* 订单表 order */
CREATE
DATABASE maomao_mall_order;
CREATE TABLE maomao_mall_order.`mao_order`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `optimistic`   INT          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `order_no`     VARCHAR(128) NOT NULL COMMENT '订单编号',
    `status`       TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '订单状态 0-待支付，1-支付完成，2-已取消，3-部分退款，4-退款',
    `order_price`  BIGINT       NOT NULL DEFAULT '0' COMMENT '订单金额',
    `payment`      BIGINT       NOT NULL DEFAULT '0' COMMENT '支付金额',
    `pay_way`      TINYINT ( 1 ) NOT NULL COMMENT '支付方式 0-余额',
    `pay_time`     datetime              DEFAULT NULL COMMENT '支付时间',
    `pay_order_no` VARCHAR(128)          DEFAULT NULL COMMENT '付款单号',
    `payer_uid`    VARCHAR(64)           DEFAULT NULL COMMENT '付款方UID',
    `order_source` INT          NOT NULL COMMENT '订单来源 0-微信小程序',
    `creator`      VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time`  datetime     NOT NULL COMMENT '创建时间',
    `updator`      VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time`  datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '订单表';
CREATE TABLE maomao_mall_order.`mao_order_detail`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `optimistic`   INT          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `order_no`     VARCHAR(128) NOT NULL COMMENT '订单编号',
    `business_id`  BIGINT       NOT NULL COMMENT '商家ID',
    `store_id`     BIGINT       NOT NULL COMMENT '店铺ID',
    `product_id`   BIGINT       NOT NULL COMMENT '商品ID',
    `product_name` VARCHAR(256) NOT NULL COMMENT '商品名称',
    `sku_code`     VARCHAR(64)  NOT NULL COMMENT 'sku编码',
    `product_type` TINYINT ( 1 ) NOT NULL COMMENT '商品类型',
    `price`        BIGINT       NOT NULL COMMENT '店铺商品sku价格',
    `goods_num`    INT          NOT NULL COMMENT '商品数量',
    `payment`      BIGINT       NOT NULL DEFAULT '0' COMMENT '支付金额',
    `beneficiary`  BIGINT                DEFAULT NULL COMMENT '收款方ID',
    `creator`      VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time`  datetime     NOT NULL COMMENT '创建时间',
    `updator`      VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time`  datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '订单明细表';
CREATE TABLE maomao_mall_order.`mao_order_cart`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
    `optimistic`  INT          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `uid`         VARCHAR(64)  NOT NULL COMMENT '用户ID',
    `business_id` BIGINT       NOT NULL COMMENT '商家ID',
    `store_id`    BIGINT       NOT NULL COMMENT '店铺ID',
    `store_name`  VARCHAR(256) NOT NULL COMMENT '店铺名称',
    `product_id`  BIGINT       NOT NULL COMMENT '商品ID',
    `sku_code`    VARCHAR(64)  NOT NULL COMMENT 'sku编码',
    `num`         INT          NOT NULL COMMENT '数量',
    `creator`     VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '订单购物车表';

/* 商家表 merchant */
CREATE
DATABASE maomao_mall_merchant;
CREATE TABLE maomao_mall_merchant.`mao_business`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '商家ID',
    `business_code` VARCHAR(64)  NOT NULL COMMENT '商家编码',
    `name`          VARCHAR(256) NOT NULL COMMENT '商家名称',
    `mobile`        VARCHAR(16)  NOT NULL COMMENT '商家电话',
    `address`       VARCHAR(256) DEFAULT NULL COMMENT '商家地址',
    `business_desc` text         DEFAULT NULL COMMENT '商家描述',
    `logo`          VARCHAR(256) DEFAULT NULL COMMENT '商家LOGO',
    `email`         VARCHAR(256) DEFAULT NULL COMMENT '商家邮箱',
    `status`        TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '商家状态 0-待激活，1-正常，2-禁用',
    `refuse_reason` VARCHAR(256) DEFAULT NULL COMMENT '拒绝原因',
    `auditor`       VARCHAR(64)  DEFAULT NULL COMMENT '审核人',
    `audit_time`    datetime     DEFAULT NULL COMMENT '审核时间',
    `creator`       VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time`   datetime     NOT NULL COMMENT '创建时间',
    `updator`       VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time`   datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '商家表';
CREATE TABLE maomao_mall_merchant.`mao_store`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
    `store_code`      VARCHAR(64)  NOT NULL COMMENT '店铺编码',
    `business_id`     BIGINT                DEFAULT NULL COMMENT '商家ID',
    `name`            VARCHAR(256) NOT NULL COMMENT '店铺名称',
    `store_type`      TINYINT ( 1 ) NOT NULL COMMENT '店铺类型',
    `mobile`          VARCHAR(16)  NOT NULL COMMENT '店铺电话',
    `category_id`     BIGINT       NOT NULL COMMENT '类目ID',
    `address`         VARCHAR(256)          DEFAULT NULL COMMENT '店铺地址',
    `store_desc`      text                  DEFAULT NULL COMMENT '店铺描述',
    `logo`            VARCHAR(256)          DEFAULT NULL COMMENT '店铺LOGO',
    `back_img`        VARCHAR(64)           DEFAULT NULL COMMENT '店铺主页背景图',
    `email`           VARCHAR(256)          DEFAULT NULL COMMENT '店铺邮箱',
    `status`          TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '店铺状态 0-待激活，1-运营中，2-禁用',
    `refuse_reason`   VARCHAR(256)          DEFAULT NULL COMMENT '拒绝原因',
    `auditor`         VARCHAR(64)           DEFAULT NULL COMMENT '审核人',
    `audit_time`      datetime              DEFAULT NULL COMMENT '审核时间',
    `beneficiary`     BIGINT       NOT NULL COMMENT '收款账户',
    `delivery`        TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '是否支持外送 0-不支持，1-支持',
    `favorite_num`    BIGINT       NOT NULL DEFAULT '0' COMMENT '收藏数量',
    `open_start_time` VARCHAR(8)   NOT NULL COMMENT '营业开始时间',
    `open_end_time`   VARCHAR(8)   NOT NULL COMMENT '营业结束时间',
    `longitude`       VARCHAR(32)           DEFAULT NULL COMMENT '经度',
    `latitude`        VARCHAR(32)           DEFAULT NULL COMMENT '纬度',
    `creator`         VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `updator`         VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time`     datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '店铺表';

/* 商品表 goods */
CREATE
DATABASE maomao_mall_goods;
CREATE TABLE maomao_mall_goods.`mao_product`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `name`          VARCHAR(256) NOT NULL COMMENT '商品名称',
    `product_code`  VARCHAR(64)  NOT NULL COMMENT '商品编码',
    `price`         BIGINT       NOT NULL COMMENT '商品价格',
    `weight`        INT          NOT NULL DEFAULT '0' COMMENT '权重',
    `category_id`   BIGINT       NOT NULL COMMENT '类目ID',
    `product_type`  TINYINT ( 1 ) NOT NULL COMMENT '商品类型',
    `status`        TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '商品状态 0-待激活，1-启用，2-停用',
    `refuse_reason` VARCHAR(256)          DEFAULT NULL COMMENT '拒绝原因',
    `auditor`       VARCHAR(64)           DEFAULT NULL COMMENT '审核人',
    `audit_time`    datetime              DEFAULT NULL COMMENT '审核时间',
    `creator`       VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time`   datetime     NOT NULL COMMENT '创建时间',
    `updator`       VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time`   datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '商品表';
CREATE TABLE maomao_mall_goods.`mao_product_sku`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'skuID',
    `product_id`    BIGINT      NOT NULL COMMENT '商品ID',
    `sku_code`      VARCHAR(64) NOT NULL COMMENT 'sku编码',
    `price`         BIGINT      NOT NULL COMMENT '售价',
    `specification` VARCHAR(16) NOT NULL COMMENT '规格值',
    `status`        TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT 'sku状态 0-启用，1-禁用',
    `creator`       VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time`   datetime    NOT NULL COMMENT '创建时间',
    `updator`       VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time`   datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '商品规格表';
CREATE TABLE maomao_mall_goods.`mao_category`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT COMMENT '类目ID',
    `category_code` VARCHAR(64) NOT NULL COMMENT '类目编码',
    `name`          VARCHAR(32) NOT NULL COMMENT '类目名称',
    `status`        TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '类目状态 0-启用，1-禁用',
    `pic`           VARCHAR(256) DEFAULT NULL COMMENT '类目图片',
    `c_level`       TINYINT ( 1 ) NOT NULL COMMENT '类目等级',
    `parent_id`     BIGINT      NOT NULL COMMENT '父类目ID',
    `creator`       VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time`   datetime    NOT NULL COMMENT '创建时间',
    `updator`       VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time`   datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '类目表';
CREATE TABLE maomao_mall_goods.`mao_store_product`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '店铺商品ID',
    `business_id`     BIGINT       NOT NULL COMMENT '商家ID',
    `store_id`        BIGINT       NOT NULL COMMENT '店铺ID',
    `product_id`      BIGINT       NOT NULL COMMENT '商品ID',
    `product_code`    VARCHAR(64)  NOT NULL COMMENT '商品编码',
    `category_id`     BIGINT       NOT NULL COMMENT '类目ID',
    `product_type`    TINYINT ( 1 ) NOT NULL COMMENT '商品类型',
    `brand_id`        BIGINT                DEFAULT NULL COMMENT '品牌ID',
    `alias`           VARCHAR(256) NOT NULL COMMENT '商品别名',
    `price`           BIGINT       NOT NULL COMMENT '店铺商品价格',
    `pic`             VARCHAR(256) NOT NULL COMMENT '商品主图',
    `label`           VARCHAR(16)           DEFAULT NULL COMMENT '商品主标签',
    `product_desc`    text                  DEFAULT NULL COMMENT '商品描述',
    `use_explain`     text                  DEFAULT NULL COMMENT '使用说明',
    `stock`           INT          NOT NULL DEFAULT '0' COMMENT '库存',
    `weight`          INT          NOT NULL DEFAULT '0' COMMENT '店铺商品权重',
    `sale_status`     TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '商品销售状态 0-销售中，1-售罄，2-下架，3-停用',
    `sale_volume`     BIGINT       NOT NULL DEFAULT '0' COMMENT '销量',
    `hot`             BIGINT       NOT NULL DEFAULT '0' COMMENT '热度',
    `return_policy`   TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '退货政策 0-不允许退货，1-七天无理由退货，2-七天有理由退货',
    `exchange_policy` TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '换货政策 0-不允许换货，1-七天无理由换货，2-七天有理由换货',
    `creator`         VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `updator`         VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time`     datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '店铺商品表';
CREATE TABLE maomao_mall_goods.`mao_store_product_sku`
(
    `id`               BIGINT      NOT NULL AUTO_INCREMENT COMMENT '店铺skuID',
    `business_id`      BIGINT      NOT NULL COMMENT '商家ID',
    `store_id`         BIGINT      NOT NULL COMMENT '店铺ID',
    `product_id`       BIGINT      NOT NULL COMMENT '商品ID',
    `sku_id`           BIGINT      NOT NULL COMMENT 'skuID',
    `sku_code`         VARCHAR(64) NOT NULL COMMENT 'sku编码',
    `store_product_id` BIGINT      NOT NULL COMMENT '店铺商品ID',
    `sale_status`      TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '商品sku销售状态 0-销售中，1-售罄，2-下架，3-停用',
    `stock`            INT         NOT NULL DEFAULT '0' COMMENT '库存',
    `lock_stock`       INT         NOT NULL DEFAULT '0' COMMENT '锁定库存',
    `price`            BIGINT      NOT NULL COMMENT '店铺商品sku价格',
    `specification`    VARCHAR(16) NOT NULL COMMENT '规格值',
    `sale_volume`      BIGINT      NOT NULL DEFAULT '0' COMMENT '销量',
    `hot`              BIGINT      NOT NULL DEFAULT '0' COMMENT '热度',
    `creator`          VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time`      datetime    NOT NULL COMMENT '创建时间',
    `updator`          VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time`      datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '店铺商品规格表';
CREATE TABLE maomao_mall_goods.`mao_carousel`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_name` VARCHAR(256) NOT NULL COMMENT '商品名称',
    `product_img`  VARCHAR(256) NOT NULL COMMENT '商品主图',
    `product_code` VARCHAR(64)  NOT NULL COMMENT '商品编码',
    `creator`      VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time`  datetime     NOT NULL COMMENT '创建时间',
    `updator`      VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time`  datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb3 COMMENT = '首页轮播图';

/* 支付表 payment */
CREATE
DATABASE maomao_mall_payment;
CREATE TABLE maomao_mall_payment.`mao_account`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT COMMENT '账户ID',
    `optimistic`    INT         NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `type`          TINYINT ( 1 ) NOT NULL COMMENT '账户类型 0-个人，1-平台，2-商家，3-店铺',
    `corelation_id` VARCHAR(64) NOT NULL COMMENT '类型关联ID',
    `balance`       BIGINT      NOT NULL DEFAULT '0' COMMENT '账户余额',
    `pay_pass`      VARCHAR(32) NOT NULL DEFAULT '123456' COMMENT '支付密码',
    `acc_status`    TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '账户状态 0-正常，1-可支付不可提现，2-可提现不可支付，3-冻结',
    `creator`       VARCHAR(64) NOT NULL COMMENT '创建人',
    `create_time`   datetime    NOT NULL COMMENT '创建时间',
    `updator`       VARCHAR(64) NOT NULL COMMENT '更新人',
    `update_time`   datetime    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb3 COMMENT = '支付账户表';
CREATE TABLE maomao_mall_payment.`mao_order_pay`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `optimistic`  INT          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `pay_no`      VARCHAR(128) NOT NULL COMMENT '支付单号',
    `order_no`    VARCHAR(128) NOT NULL COMMENT '订单编号',
    `payment`     BIGINT       NOT NULL COMMENT '支付金额',
    `pay_way`     TINYINT ( 1 ) NOT NULL COMMENT '支付方式 0-余额',
    `pay_acc`     BIGINT                DEFAULT NULL COMMENT '付款账户',
    `creator`     VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb3 COMMENT = '订单支付表';
CREATE TABLE maomao_mall_payment.`mao_order_pay_detail`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `optimistic`  INT          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `pay_no`      VARCHAR(128) NOT NULL COMMENT '支付单号',
    `order_no`    VARCHAR(128) NOT NULL COMMENT '订单编号',
    `payment`     BIGINT       NOT NULL COMMENT '支付金额',
    `pay_way`     TINYINT ( 1 ) NOT NULL COMMENT '支付方式 0-余额',
    `pay_acc`     BIGINT                DEFAULT NULL COMMENT '付款账户',
    `beneficiary` BIGINT       NOT NULL COMMENT '收款账户',
    `business_id` BIGINT       NOT NULL COMMENT '商家ID',
    `store_id`    BIGINT       NOT NULL COMMENT '店铺ID',
    `product_id`  BIGINT       NOT NULL COMMENT '商品ID',
    `sku_code`    VARCHAR(64)  NOT NULL COMMENT 'sku编码',
    `price`       BIGINT       NOT NULL COMMENT '商品sku价格',
    `num`         INT          NOT NULL COMMENT '数量',
    `creator`     VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb3 COMMENT = '订单支付明细表';
CREATE TABLE maomao_mall_payment.`mao_order_refund`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `optimistic`  INT          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    `refund_no`   VARCHAR(128) NOT NULL COMMENT '退款单号',
    `order_no`    VARCHAR(128) NOT NULL COMMENT '订单编号',
    `creator`     VARCHAR(64)  NOT NULL COMMENT '创建人',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `updator`     VARCHAR(64)  NOT NULL COMMENT '更新人',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb3 COMMENT = '订单退款表';