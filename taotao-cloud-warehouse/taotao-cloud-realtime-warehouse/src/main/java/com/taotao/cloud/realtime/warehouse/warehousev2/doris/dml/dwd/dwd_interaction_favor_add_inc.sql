-- DROP TABLE IF EXISTS dwd.dwd_interaction_favor_add_inc;
CREATE  TABLE dwd.dwd_interaction_favor_add_inc
(
    `id`          VARCHAR(255) COMMENT '编号',
    `k1`          DATE NOT NULL   COMMENT '分区字段',
    `user_id`     STRING COMMENT '用户id',
    `sku_id`      STRING COMMENT 'sku_id',
    `date_id`     STRING COMMENT '日期id',
    `create_time` STRING COMMENT '收藏时间'
)
    ENGINE=OLAP
UNIQUE KEY(`id`,`k1`)
COMMENT '收藏事实表'
PARTITION BY RANGE(`k1`) ()
DISTRIBUTED BY HASH(`id`)
PROPERTIES
(
    "replication_allocation" = "tag.location.default: 1",
    "is_being_synced" = "false",
    "storage_format" = "V2",
    "light_schema_change" = "true",
    "disable_auto_compaction" = "false",
    "enable_single_replica_compaction" = "false",
    "dynamic_partition.enable" = "true",
    "dynamic_partition.time_unit" = "DAY",
    "dynamic_partition.start" = "-60",
    "dynamic_partition.end" = "3",
    "dynamic_partition.prefix" = "p",
    "dynamic_partition.buckets" = "32",
    "dynamic_partition.create_history_partition" = "true"
);