CREATE DATABASE IF NOT EXISTS `kintex_hkpsi` DEFAULT CHARACTER SET `utf8mb4` COLLATE `utf8mb4_general_ci`;
USE `kintex_erp_dev`;

CREATE TABLE `hkpsi_bid`
(
    `bid_id`         BIGINT UNSIGNED PRIMARY KEY        NOT NULL COMMENT '投标编号',
    `bid_date`       DATE                               NOT NULL COMMENT '投标日期',
    `vendor_id`      INT UNSIGNED                       NOT NULL COMMENT '供应商',
    `vendor_program` SMALLINT                           NULL COMMENT '项目',
    `file_name`      VARCHAR(255)                       NOT NULL COMMENT '文件名',
    `currency`       VARCHAR(10)                        NOT NULL COMMENT '货币',
    `exchange_rate`  DECIMAL(13, 4)                     NOT NULL COMMENT '投标汇率',
    `created_at`     DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`     INT UNSIGNED                       NOT NULL,
    `updated_at`     DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`     INT UNSIGNED                       NOT NULL
);

CREATE TABLE `hkpsi_bid_record`
(
    `record_id`           BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT  NOT NULL COMMENT '清单id',
    `bid_id`              BIGINT UNSIGNED                             NOT NULL COMMENT '投标id',
    `bid_qty`             SMALLINT UNSIGNED DEFAULT 0                 NULL COMMENT '投标数量',
    `bid_price`           DECIMAL(13, 4)    DEFAULT 0                 NULL COMMENT '投标单价',
    `bid_hkd`             DECIMAL(13, 4)    DEFAULT 0                 NULL COMMENT '投标港币',
    `bid_sum`             DECIMAL(13, 4)    DEFAULT 0.0000            NULL COMMENT '投标合计',
    `bid_high`            DECIMAL(13, 4)    DEFAULT 0.0000            NULL COMMENT '往期中标价',
    `won_id`              BIGINT UNSIGNED   DEFAULT 0                 NULL COMMENT '中标id',
    `won_qty`             SMALLINT UNSIGNED DEFAULT 0                 NULL COMMENT '中标数量',
    `won_price`           DECIMAL(13, 4)    DEFAULT 0                 NULL COMMENT '中标单价',
    `won_hkd`             DECIMAL(13, 4)    DEFAULT 0                 NULL COMMENT '中标港币',
    `won_sum`             DECIMAL(13, 4)    DEFAULT 0.0000            NOT NULL COMMENT '中标合计',
    `tag`                 VARCHAR(1024)     DEFAULT ''                NOT NULL,
    `source_warehouse`    VARCHAR(255)      DEFAULT ''                NULL,
    `source_sku`          VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_oem`          VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_description`  VARCHAR(255)      DEFAULT ''                NULL,
    `source_model_number` VARCHAR(255)      DEFAULT ''                NULL,
    `source_model_name`   VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_model`        VARCHAR(255)      DEFAULT ''                NULL,
    `source_capacity`     VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_carrier`      VARCHAR(255)      DEFAULT ''                NULL,
    `source_color`        VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_condition`    VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_grade`        VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_FMiP_locked`  VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_SIM_locked`   VARCHAR(255)      DEFAULT ''                NOT NULL,
    `source_LCD_health`   VARCHAR(255)      DEFAULT ''                NOT NULL,
    `created_at`          DATETIME          DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`          INT UNSIGNED                                NOT NULL,
    `updated_at`          DATETIME          DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`          INT UNSIGNED                                NOT NULL
);



CREATE TABLE `hkpsi_brand`
(
    `brand_id`          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY                        NOT NULL,
    `brand_name`        VARCHAR(255) UNIQUE                                            NOT NULL,
    `brand_name_locale` JSON             DEFAULT JSON_OBJECT('zh_CN', '', 'en_US', '') NOT NULL,
    `brand_logo`        VARCHAR(255)                                                   NULL,
    `disabled`          TINYINT UNSIGNED DEFAULT 0                                     NOT NULL,
    `created_at`        DATETIME         DEFAULT CURRENT_TIMESTAMP                     NOT NULL,
    `created_by`        INT UNSIGNED                                                   NOT NULL,
    `updated_at`        DATETIME         DEFAULT CURRENT_TIMESTAMP                     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`        INT UNSIGNED                                                   NOT NULL
);

CREATE TABLE `hkpsi_carrier`
(
    `carrier_id`   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY    NOT NULL COMMENT '运营商编号',
    `carrier_name` VARCHAR(50) UNIQUE                         NOT NULL COMMENT '运营商姓名',
    `disabled`     TINYINT UNSIGNED DEFAULT 0                 NOT NULL,
    `created_at`   DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `created_by`   INT UNSIGNED                               NOT NULL,
    `updated_at`   DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by`   INT UNSIGNED                               NOT NULL
);


CREATE TABLE `hkpsi_category`
(
    `category_id`   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY    NOT NULL,
    `category_name` VARCHAR(255) UNIQUE                        NOT NULL,
    `disabled`      TINYINT UNSIGNED DEFAULT 0                 NOT NULL,
    `created_at`    DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`    INT UNSIGNED                               NOT NULL,
    `updated_at`    DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`    INT UNSIGNED                               NOT NULL
);

CREATE TABLE `hkpsi_client`
(
    `client_id`      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY    NOT NULL,
    `client_name`    VARCHAR(50) UNIQUE                         NOT NULL COMMENT '顾客名称',
    `client_contact` VARCHAR(20)                                NULL COMMENT '顾客联系方式',
    `disabled`       TINYINT UNSIGNED DEFAULT 0                 NOT NULL,
    `created_at`     DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`     INT UNSIGNED                               NOT NULL,
    `updated_at`     DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`     INT UNSIGNED                               NOT NULL
);

CREATE TABLE `hkpsi_currency`
(
    `id`              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY    NOT NULL COMMENT '汇率id',
    `currency_code`   VARCHAR(50)                                NOT NULL COMMENT '货币',
    `currency_name`   VARCHAR(50)      DEFAULT ''                NOT NULL COMMENT '货币名称',
    `currency_symbol` VARCHAR(10)                                NOT NULL COMMENT '货币符号',
    `exchange_rate`   DECIMAL(13, 4)                             NULL COMMENT '汇率',
    `disabled`        TINYINT UNSIGNED DEFAULT 0                 NOT NULL,
    `created_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`      INT UNSIGNED                               NOT NULL,
    `updated_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`      INT UNSIGNED                               NOT NULL
);


CREATE TABLE `hkpsi_goods`
(
    `goods_id`        BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY     NOT NULL COMMENT '商品编号',
    `sale_order`      BIGINT UNSIGNED                                NOT NULL COMMENT '订单编号',
    `won_bid_number`  BIGINT UNSIGNED  DEFAULT 0                     NOT NULL COMMENT '中标号',
    `sku_id`          INT UNSIGNED     DEFAULT 0                     NOT NULL COMMENT '我们的sku',
    `price`           DECIMAL(13, 4)                                 NULL COMMENT '拟定的商品单价',
    `imei1`           VARCHAR(50)      DEFAULT ''                    NOT NULL COMMENT '电话设备识别码1',
    `imei2`           VARCHAR(50)      DEFAULT ''                    NOT NULL COMMENT '电话设备识别码2',
    `locked`          TINYINT UNSIGNED DEFAULT 0                     NOT NULL COMMENT 'SIM锁',
    `icloud`          TINYINT UNSIGNED DEFAULT 0                     NOT NULL COMMENT 'iCloud锁',
    `device_in_time`  DATETIME         DEFAULT '1970-01-01 00:00:00' NOT NULL COMMENT '设备入库时间',
    `device_out_time` DATETIME         DEFAULT '1970-01-01 00:00:00' NOT NULL COMMENT '设备出库时间',
    `grade`           VARCHAR(100)     DEFAULT ''                    NOT NULL COMMENT '自定义等级',
    `disabled`        TINYINT UNSIGNED DEFAULT '0'                   NOT NULL,
    `created_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP     NOT NULL COMMENT '创建时间',
    `created_by`      INT UNSIGNED                                   NOT NULL,
    `updated_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP     NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by`      INT UNSIGNED                                   NOT NULL
) COMMENT '设备表';

CREATE TABLE `hkpsi_inventory`
(
    `sku_id`             BIGINT UNSIGNED PRIMARY KEY            NOT NULL,
    `inventory_quantity` INT UNSIGNED DEFAULT 0                 NOT NULL COMMENT '库存数',
    `created_at`         DATETIME     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`         INT UNSIGNED                           NOT NULL,
    `updated_at`         DATETIME     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`         INT UNSIGNED                           NOT NULL
) COMMENT '库存表';

CREATE TABLE `hkpsi_payment_record`
(
    `won_number`     VARCHAR(255) UNIQUE                 NOT NULL COMMENT '中标单号',
    `pay_date`       DATE           DEFAULT '1970-01-01' NOT NULL COMMENT '付款日期',
    `amount_payable` DECIMAL(13, 4)                      NOT NULL COMMENT '应付金额',
    `use_balance`    DECIMAL(13, 4) DEFAULT 0.0000       NOT NULL COMMENT '使用余额',
    `amount_paid`    DECIMAL(13, 4) DEFAULT 0.0000       NOT NULL COMMENT '实付金额',
    `operator`       INT UNSIGNED                        NOT NULL COMMENT '操作人员'
);

CREATE TABLE `hkpsi_sale_order`
(
    `sale_order_id`   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY     NOT NULL,
    `client_id`       INT UNSIGNED                                   NOT NULL COMMENT '客户id',
    `salesman`        VARCHAR(50)                                    NOT NULL COMMENT '销售人',
    `count_price`     DECIMAL(13, 4)                                 NULL COMMENT '总价',
    `handling_charge` DECIMAL(13, 4)                                 NULL COMMENT '手续费',
    `deposit`         DECIMAL(13, 4)   DEFAULT 0.0000                NOT NULL COMMENT '定金',
    `other_price`     DECIMAL(13, 4)                                 NULL COMMENT '其他收费',
    `remark`          VARCHAR(255)     DEFAULT ''                    NOT NULL COMMENT '备注',
    `pay_money_date`  DATETIME         DEFAULT '1970-01-01 00:00:00' NOT NULL COMMENT '结款时间',
    `date`            DATE             DEFAULT '1970-01-01'          NOT NULL COMMENT '销售时间',
    `exchange_rate`   DECIMAL(8, 4)    DEFAULT 1.0000                NOT NULL COMMENT '订单汇率',
    `disabled`        TINYINT UNSIGNED DEFAULT '0'                   NOT NULL,
    `created_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP     NOT NULL,
    `created_by`      INT UNSIGNED                                   NOT NULL,
    `updated_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`      INT UNSIGNED                                   NOT NULL
);

CREATE TABLE `hkpsi_sales_list`
(
    `sale_list_id` BIGINT UNSIGNED                            NOT NULL COMMENT '销售报表id' PRIMARY KEY,
    `won_bid_id`   BIGINT UNSIGNED                            NOT NULL COMMENT '中标单号一对多',
    `warehouse`    VARCHAR(30)                                NOT NULL COMMENT '供应商仓库',
    `item`         VARCHAR(30)                                NOT NULL COMMENT '供应商产品编号',
    `description`  VARCHAR(255)                               NOT NULL COMMENT '产品标号',
    `brand`        VARCHAR(255)                               NOT NULL COMMENT '品牌',
    `model_name`   VARCHAR(255)                               NOT NULL COMMENT '型号名称',
    `model_number` VARCHAR(255)                               NOT NULL COMMENT '型号号码',
    `capacity`     INT UNSIGNED                               NOT NULL COMMENT '容量',
    `color`        VARCHAR(255)     DEFAULT ''                NOT NULL COMMENT '颜色',
    `carrier`      VARCHAR(50)                                NOT NULL COMMENT '运营商',
    `icloud`       TINYINT          DEFAULT 0                 NOT NULL COMMENT '云端存储',
    `sim_status`   TINYINT          DEFAULT 0                 NOT NULL COMMENT 'SIM卡状态',
    `grade`        VARCHAR(255)                               NOT NULL COMMENT '我们的商品等级',
    `quantity`     INT UNSIGNED                               NOT NULL COMMENT '数量',
    `cost_price`   DECIMAL(6, 2)                              NOT NULL COMMENT '单个成本价',
    `total_cost`   DECIMAL(10, 2)                             NOT NULL COMMENT '总成本价',
    `hkd`          DECIMAL(7, 2)                              NOT NULL COMMENT '港币价',
    `asp`          DECIMAL(7, 2)                              NOT NULL COMMENT '销售拟定单价',
    `count_price`  DECIMAL(10, 2)                             NOT NULL COMMENT '销售员拟定后总价',
    `unit_profit`  DECIMAL(6, 2)                              NOT NULL COMMENT '单个利润',
    `count_profit` DECIMAL(9, 2)                              NOT NULL COMMENT '总利润',
    `gpm`          DECIMAL(5, 3)                              NOT NULL COMMENT '盈利百分比',
    `client_name`  VARCHAR(50)      DEFAULT ''                NOT NULL COMMENT '顾客',
    `salesman`     VARCHAR(50)      DEFAULT ''                NOT NULL COMMENT '销售人',
    `sale_date`    DATE             DEFAULT '1970-01-01'      NOT NULL COMMENT '销售时间',
    `disabled`     TINYINT UNSIGNED DEFAULT '0'               NOT NULL,
    `created_at`   DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `created_by`   INT UNSIGNED                               NOT NULL,
    `updated_at`   DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by`   INT UNSIGNED                               NOT NULL
);

CREATE TABLE `hkpsi_sales_list_collect`
(
    `id`             INT UNSIGNED AUTO_INCREMENT PRIMARY KEY    NOT NULL COMMENT '销售汇总id',
    `sale_order_id`  BIGINT UNSIGNED                            NOT NULL COMMENT '销售报表id',
    `count_quantity` INT UNSIGNED                               NOT NULL COMMENT '商品总数量',
    `total_usd`      DECIMAL(13, 2)                             NOT NULL COMMENT '美元总价',
    `exchange_rate`  DECIMAL(8, 4)                              NULL COMMENT '汇率',
    `total_hkd`      DECIMAL(15, 2)                             NOT NULL COMMENT '港元总价',
    `freight`        DECIMAL(6, 2)                              NOT NULL COMMENT '运费',
    `total_cost`     DECIMAL(10, 2)                             NOT NULL COMMENT '总成本价',
    `total_sales`    DECIMAL(15, 2)                             NOT NULL COMMENT '总销售额',
    `margin`         DECIMAL(10, 2)                             NOT NULL COMMENT '盈利',
    `gpm`            DECIMAL(5, 3)                              NOT NULL COMMENT '盈利百分比',
    `disabled`       TINYINT UNSIGNED DEFAULT '0'               NOT NULL,
    `created_at`     DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `created_by`     INT UNSIGNED                               NOT NULL,
    `updated_at`     DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by`     INT UNSIGNED                               NOT NULL
);

CREATE TABLE `hkpsi_sku`
(
    `sku_id`     BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `spu_id`     INT UNSIGNED                               NOT NULL,
    `attribute`  VARCHAR(255)                               NOT NULL COMMENT '属性',
    `disabled`   TINYINT UNSIGNED DEFAULT '0'               NOT NULL,
    `created_at` DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by` INT UNSIGNED                               NOT NULL,
    `updated_at` DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` INT UNSIGNED                               NOT NULL,
    CONSTRAINT `sku_text`
        UNIQUE (`spu_id`, `attribute`)
);

CREATE TABLE `hkpsi_sku_attr_key`
(
    `key_id`      INT UNSIGNED AUTO_INCREMENT
        PRIMARY KEY,
    `category_id` INT UNSIGNED                               NOT NULL,
    `attr_name`   VARCHAR(255)                               NOT NULL,
    `description` VARCHAR(255)                               NOT NULL,
    `disabled`    TINYINT UNSIGNED DEFAULT '0'               NOT NULL,
    `created_at`  DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`  INT UNSIGNED                               NOT NULL,
    `updated_at`  DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`  INT UNSIGNED                               NOT NULL,
    CONSTRAINT `attr_name`
        UNIQUE (`category_id`, `attr_name`)
);

CREATE TABLE `hkpsi_sku_attribute`
(
    `attribute_id`   INT UNSIGNED AUTO_INCREMENT
        PRIMARY KEY,
    `category_id`    INT UNSIGNED NOT NULL,
    `attribute_name` VARCHAR(255) NOT NULL,
    `description`    VARCHAR(255) NOT NULL,
    CONSTRAINT `attr_name`
        UNIQUE (`category_id`, `attribute_name`)
);

CREATE TABLE `hkpsi_spu`
(
    `spu_id`     INT UNSIGNED AUTO_INCREMENT
        PRIMARY KEY,
    `spu_name`   VARCHAR(255)                               NOT NULL,
    `spu_brand`  VARCHAR(255)                               NOT NULL COMMENT '品牌',
    `category`   VARCHAR(255)                               NOT NULL COMMENT '种类',
    `spec`       VARCHAR(255)                               NOT NULL COMMENT '描述',
    `disabled`   TINYINT UNSIGNED DEFAULT '0'               NOT NULL,
    `created_at` DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by` INT UNSIGNED                               NOT NULL,
    `updated_at` DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` INT UNSIGNED                               NOT NULL,
    CONSTRAINT `spu_text`
        UNIQUE (`spu_brand`, `spu_name`)
);

CREATE TABLE `hkpsi_stage`
(
    `id`         BIGINT UNSIGNED AUTO_INCREMENT
        PRIMARY KEY,
    `won_id`     BIGINT UNSIGNED                    NOT NULL COMMENT '中标编号',
    `imei`       VARCHAR(20)                        NOT NULL COMMENT '唯一标识码',
    `vendor_sku` BIGINT UNSIGNED                    NOT NULL COMMENT '供应商SKU',
    `tag`        VARCHAR(30)                        NOT NULL COMMENT '用于扫码',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by` INT UNSIGNED                       NOT NULL,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` INT UNSIGNED                       NOT NULL,
    CONSTRAINT `imei`
        UNIQUE (`imei`),
    CONSTRAINT `tag`
        UNIQUE (`tag`)
) COMMENT '虚拟库存';

CREATE TABLE `hkpsi_user`
(
    `user_id`        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `user_password`  VARCHAR(100)                            NOT NULL COMMENT '密码',
    `employee_no`    VARCHAR(100) UNIQUE                     NOT NULL COMMENT '工號',
    `employee_name`  VARCHAR(50)                             NOT NULL COMMENT '姓名',
    `employee_email` VARCHAR(100) UNIQUE                     NULL COMMENT '邮箱',
    `company_name`   VARCHAR(50) DEFAULT ''                  NOT NULL COMMENT '公司名称',
    `disabled`       TINYINT     DEFAULT 0                   NOT NULL COMMENT '禁用',
    `access`         JSON                                    NULL COMMENT '角色权限',
    `created_at`     DATETIME    DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    `created_by`     INT UNSIGNED                            NOT NULL,
    `updated_at`     DATETIME    DEFAULT CURRENT_TIMESTAMP   NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`     INT UNSIGNED                            NOT NULL
);

CREATE TABLE `hkpsi_vendor`
(
    `vendor_id`   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY    NOT NULL COMMENT '供应商编号',
    `vendor_code` VARCHAR(50)                                NOT NULL COMMENT '供应商代码',
    `vendor_name` VARCHAR(50)                                NOT NULL COMMENT '供应商名称',
    `warehouse`   VARCHAR(255)     DEFAULT ''                NOT NULL COMMENT '供应商仓库',
    `currency`    INT                                        NULL COMMENT '货币 ID',
    `disabled`    TINYINT UNSIGNED DEFAULT 0               NOT NULL,
    `created_at`  DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`  INT UNSIGNED                               NOT NULL,
    `updated_at`  DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`  INT UNSIGNED                               NOT NULL
);

CREATE TABLE IF NOT EXISTS `hkpsi_vendor_program`
(
    `program_id`     SMALLINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '项目id',
    `program_name`   VARCHAR(255)                                 NOT NULL COMMENT '项目名称',
    `program_vendor` INT UNSIGNED                                 NOT NULL COMMENT '所属的供应商',
    `disabled`       TINYINT UNSIGNED DEFAULT 0                   NOT NULL,
    `created_at`     DATETIME         DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    `created_by`     INT UNSIGNED                                 NOT NULL,
    `updated_at`     DATETIME         DEFAULT CURRENT_TIMESTAMP   NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`     INT UNSIGNED                                 NOT NULL
);

CREATE TABLE `hkpsi_vendor_sku`
(
    `sku_id`          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '供应商 SKU 的 Id',
    `sku_description` VARCHAR(255)                               NOT NULL COMMENT '供应商 SKU 的 description',
    `sku_grade`       VARCHAR(255)                               NOT NULL COMMENT '供应商 SKU 的 grade',
    `sku_tag`         VARCHAR(255)                               NOT NULL COMMENT 'description|grade',
    `vendor_id`       INT UNSIGNED                               NOT NULL COMMENT '供应商的 Id',
    `k_sku_id`        BIGINT UNSIGNED                            NOT NULL COMMENT 'Kintex SKU Id',
    `disabled`        TINYINT UNSIGNED DEFAULT 0               NOT NULL,
    `created_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`      INT UNSIGNED                               NOT NULL,
    `updated_at`      DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`      INT UNSIGNED                               NOT NULL,
    CONSTRAINT `vendor_sku`
        UNIQUE (`vendor_id`, `sku_tag`)
);

CREATE TABLE `hkpsi_won_bid`
(
    `won_id`        BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `won_number`    VARCHAR(255)                               NOT NULL COMMENT '中标号',
    `won_date`      DATE                                       NOT NULL COMMENT '中标日期',
    `amount`        DECIMAL(13, 4)                             NOT NULL COMMENT '中标金额',
    `bid_id`        BIGINT UNSIGNED                            NOT NULL COMMENT '投标单',
    `stage`         TINYINT UNSIGNED DEFAULT 0                 NOT NULL COMMENT '虚拟仓库',
    `warehouse`     VARCHAR(100)     DEFAULT ''                NOT NULL COMMENT '供应商仓库',
    `exchange_rate` DECIMAL(13, 4)                             NOT NULL COMMENT '中标汇率',
    `created_at`    DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`    INT UNSIGNED                               NOT NULL,
    `updated_at`    DATETIME         DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`    INT UNSIGNED                               NOT NULL
);

CREATE TABLE `hkpsi_access`
(
    `id`          INT UNSIGNED PRIMARY KEY NOT NULL,
    `description` VARCHAR(255) DEFAULT ''  NOT NULL
);

CREATE TABLE `hkpsi_arrival_record`
(
    `record_id`                BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY    NOT NULL COMMENT '到货记录id',
    `arrival_order_number`     VARCHAR(255)    DEFAULT ''                    NOT NULL COMMENT '中标单号',
    `arrival_date`             DATE            DEFAULT '1970-01-01'          NOT NULL COMMENT '到货日期',
    `arrival_tracking`         VARCHAR(100)    DEFAULT ''                    NOT NULL COMMENT '货运单号',
    `arrival_carrier`          VARCHAR(100)    DEFAULT ''                    NOT NULL COMMENT '货运公司',
    `arrival_pieces`           INT UNSIGNED    DEFAULT 0                     NOT NULL COMMENT '件数',
    `arrival_carton_or_pallet` VARCHAR(100)    DEFAULT ''                    NOT NULL COMMENT '箱/板',
    `arrival_weight`           DOUBLE UNSIGNED DEFAULT 0                     NOT NULL COMMENT '重量(KG)',
    `arrival_receiver`         VARCHAR(100)    DEFAULT ''                    NOT NULL COMMENT '签收人',
    `arrival_delivery_time`    DATETIME        DEFAULT '1970-01-01 00:00:00' NOT NULL COMMENT '时间',
    `arrival_damage`           VARCHAR(100)    DEFAULT ''                    NOT NULL COMMENT '破损',
    `create_at`                DATETIME        DEFAULT CURRENT_TIMESTAMP     NOT NULL,
    `created_by`               INT UNSIGNED                                  NOT NULL,
    `updated_at`               DATETIME        DEFAULT CURRENT_TIMESTAMP     NOT NULL,
    `updated_by`               INT UNSIGNED                                  NOT NULL
) COMMENT '到货记录';

CREATE TABLE `hkpsi_kdn_logistics`
(
    `kdn_logistics_id`   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '快递鸟物流编号id',
    `kdn_logistics_name` VARCHAR(50)       NOT NULL COMMENT '物流公司名字',
    `kdn_logistics_code` VARCHAR(50)       NOT NULL COMMENT '快递鸟物流公司的代码',
    `disabled`           TINYINT DEFAULT 0 NOT NULL
);

CREATE TABLE `hkpsi_logistics`
(
    `logistics_id`      BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '物流id',
    `won_number`        VARCHAR(255) DEFAULT ''                    NOT NULL COMMENT '中标单号',
    `logistics_number`  VARCHAR(255) DEFAULT ''                    NOT NULL COMMENT '物流单号',
    `kdn_logistics_id`  INT UNSIGNED DEFAULT 0                     NOT NULL COMMENT '物流公司代码',
    `kdn_logistics_msg` VARCHAR(255) DEFAULT ''                    NOT NULL COMMENT '物流信息',
    `disabled`          TINYINT UNSIGNED                           NOT NULL COMMENT '是否可用',
    CONSTRAINT `hkpsi_logistics_logistics_number_uindex`
        UNIQUE (`logistics_number`),
    CONSTRAINT `hkpsi_logistics_won_number_uindex`
        UNIQUE (`won_number`)
) COMMENT '物流信息表';

CREATE TABLE `hkpsi_carton`
(
    `carton_id`     BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT 'id',
    `carton_number` INT UNSIGNED                               NOT NULL COMMENT '箱号',
    `content`       JSON                                       NOT NULL COMMENT '箱内物品汇总信息',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP         NOT NULL COMMENT '创建时间',
    `created_by`    INT UNSIGNED                               NOT NULL COMMENT '创建人',
    `updated_at`    DATETIME DEFAULT CURRENT_TIMESTAMP         NOT NULL COMMENT '修改时间',
    `updated_by`    INT UNSIGNED                               NOT NULL COMMENT '修改人'
) COMMENT '装箱信息';
CREATE INDEX `hkpsi_carton_number_index` ON `hkpsi_carton` (`carton_number`);

CREATE TABLE `hkpsi_contact_us_user`
(
    `id`           INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `name`         VARCHAR(50)  DEFAULT ''                 NOT NULL COMMENT '姓名',
    `company_name` VARCHAR(50)  DEFAULT ''                 NOT NULL COMMENT '公司名称',
    `email`        VARCHAR(100) DEFAULT ''                 NOT NULL COMMENT '邮箱',
    `password`     VARCHAR(25)  DEFAULT ''                 NOT NULL COMMENT '密码',
    `phone_model`  VARCHAR(25)  DEFAULT ''                 NOT NULL COMMENT '手机型号',
    CONSTRAINT `ksp_email_uindex`
        UNIQUE (`email`)
) COMMENT 'KintexSellPhoneSystem';

CREATE TABLE `website_wireless_contact_us`
(
    `id`         BIGINT UNSIGNED PRIMARY KEY           NOT NULL COMMENT '工单号',
    `name`       VARCHAR(25) DEFAULT ''                NOT NULL COMMENT '称呼',
    `email`      VARCHAR(50) DEFAULT ''                NOT NULL COMMENT '邮箱',
    `created_at` DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL
) COMMENT '联系我们';

CREATE TABLE `website_wireless_message`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '自增id',
    `session_id`   BIGINT UNSIGNED UNIQUE                     NOT NULL COMMENT '工单号',
    `from`         VARCHAR(50)  DEFAULT ''                    NOT NULL COMMENT '发件人',
    `to`           VARCHAR(50)  DEFAULT ''                    NOT NULL COMMENT '收件人',
    `message`      VARCHAR(255) DEFAULT ''                    NOT NULL COMMENT '信息',
    `created_time` DATETIME     DEFAULT CURRENT_TIMESTAMP     NOT NULL
) COMMENT '邮件服务';

CREATE TABLE `hkpsi_imei_record`
(
    `record_id`       BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY  NOT NULL COMMENT 'id',
    `lot_id`          VARCHAR(255)                                NULL COMMENT 'LotID',
    `brand`           VARCHAR(255)      DEFAULT ''                NOT NULL COMMENT '品牌',
    `model`           VARCHAR(255)      DEFAULT ''                NOT NULL COMMENT '模型',
    `model_name`      VARCHAR(255)      DEFAULT ''                NULL COMMENT '型号名称',
    `model_number`    VARCHAR(255)      DEFAULT ''                NULL COMMENT '型号名称',
    `imei`            VARCHAR(255)      DEFAULT ''                NOT NULL COMMENT 'IMEI',
    `imei2`           VARCHAR(255)      DEFAULT ''                NOT NULL COMMENT 'IMEI',
    `serial_number`   VARCHAR(255)      DEFAULT ''                NOT NULL COMMENT '序列号',
    `capacity`        SMALLINT UNSIGNED DEFAULT 0                 NOT NULL COMMENT '容量',
    `carrier`         VARCHAR(255)      DEFAULT ''                NULL COMMENT '运营商',
    `color`           VARCHAR(255)      DEFAULT ''                NOT NULL COMMENT '颜色',
    `source_oem`      VARCHAR(255)      DEFAULT ''                NULL COMMENT '品牌',
    `source_part_no`  VARCHAR(255)      DEFAULT ''                NULL COMMENT 'Part No.',
    `source_model`    VARCHAR(255)      DEFAULT ''                NULL COMMENT 'model',
    `source_capacity` VARCHAR(255)      DEFAULT ''                NULL COMMENT '容量文本',
    `source_carrier`  VARCHAR(255)      DEFAULT ''                NULL COMMENT '运营商',
    `source_color`    VARCHAR(255)      DEFAULT ''                NULL COMMENT '颜色',
    `source_grade`    VARCHAR(255)      DEFAULT ''                NULL COMMENT '等级',
    `source_code`     VARCHAR(255)      DEFAULT ''                NULL COMMENT 'IMEI No.',
    `source_imei2`    VARCHAR(255)      DEFAULT ''                NULL COMMENT 'IMEI 2',
    `price`           DECIMAL(13,4)     DEFAULT 0                 NOT NULL COMMENT '价格',
    `created_at`      DATETIME          DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `created_by`      INT UNSIGNED      DEFAULT 0                 NOT NULL,
    `updated_at`      DATETIME          DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`      INT UNSIGNED      DEFAULT 0                 NOT NULL
);
CREATE INDEX `hkpsi_imei_record_source_code_index`
    ON `hkpsi_imei_record` (`source_code`);


CREATE TABLE `hkpsi_inventory_goods`
(
    `record_id`       BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT 'id',
    `lot_id`          VARCHAR(255)                               NULL COMMENT 'LotID',
    `brand`           VARCHAR(255)   DEFAULT ''                  NOT NULL COMMENT '品牌',
    `model`           VARCHAR(255)   DEFAULT ''                  NOT NULL COMMENT '模型',
    `model_name`      VARCHAR(255)   DEFAULT ''                  NULL COMMENT '型号名称',
    `model_number`    VARCHAR(255)   DEFAULT ''                  NULL COMMENT '型号名称',
    `imei`            VARCHAR(255)   DEFAULT ''                  NOT NULL,
    `imei2`           VARCHAR(255)   DEFAULT ''                  NOT NULL,
    `serial_number`   VARCHAR(255)   DEFAULT ''                  NOT NULL,
    `capacity`        SMALLINT       DEFAULT 0                   NOT NULL,
    `color`           VARCHAR(255)   DEFAULT ''                  NOT NULL,
    `source_oem`      VARCHAR(255)   DEFAULT ''                  NULL COMMENT '品牌',
    `source_part_no`  VARCHAR(255)   DEFAULT ''                  NULL COMMENT 'Part No.',
    `source_model`    VARCHAR(255)   DEFAULT ''                  NULL COMMENT 'model',
    `source_capacity` VARCHAR(255)   DEFAULT ''                  NULL COMMENT '容量文本',
    `source_carrier`  VARCHAR(255)   DEFAULT ''                  NULL COMMENT '运营商',
    `source_color`    VARCHAR(255)   DEFAULT ''                  NULL COMMENT '颜色',
    `source_grade`    VARCHAR(255)   DEFAULT ''                  NULL COMMENT '等级',
    `source_code`     VARCHAR(255)   DEFAULT ''                  NULL COMMENT 'IMEI No.',
    `source_imei2`    VARCHAR(255)   DEFAULT ''                  NULL COMMENT 'IMEI 2',
    `price`           DOUBLE(13, 4)  DEFAULT 0.0000              NOT NULL COMMENT '价格',
    `hkd`             DECIMAL(13, 4) DEFAULT 0.0000              NOT NULL COMMENT '港币',
    `package_id`      BIGINT UNSIGNED                            NULL COMMENT '包装箱id',
    `url`             VARCHAR(255)                               NULL COMMENT '信息页',
    `created_at`      DATETIME       DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    `created_by`      INT UNSIGNED   DEFAULT '0'                 NOT NULL,
    `updated_at`      DATETIME       DEFAULT CURRENT_TIMESTAMP   NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`      INT UNSIGNED   DEFAULT '0'                 NOT NULL
);

CREATE INDEX `hkpsi_imei_record_source_code_index`
    ON `hkpsi_inventory_goods` (`source_code`);