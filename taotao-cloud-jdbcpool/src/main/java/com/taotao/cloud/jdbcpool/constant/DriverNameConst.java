package com.taotao.cloud.jdbcpool.constant;

/**
 * 数据库加在类名称常量
 *
 * date 2017/7/31
 * @since 2024.06
 */
public final class DriverNameConst {

    private DriverNameConst(){}

    /**
     * SQL Server 数据库
     * com.microsoft.sqlserver.jdbc.
     */
    public static final String SQL_SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * MySQL 数据库
     */
    public static final String MYSQL = "com.mysql.jdbc.Driver";

    /**
     * MYSQL 8.0 及其以后的版本
     */
    //jdbc.url=jdbc:mysql://127.0.0.1:3306/k3c?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&serverTimezone=Hongkong
    public static final String MYSQL_8 = "com.mysql.cj.jdbc.Driver";

    /**
     * Oracle 数据库
     */
    public static final String ORACLE = "oracle.jdbc.OracleDriver";

}
