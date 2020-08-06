package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC连接数据库+执行sql语句+批处理 
 * Batch
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo05 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		Statement stat = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			connection.setAutoCommit(false);//事务手动提交
			long star  = System.currentTimeMillis();
			stat = connection.createStatement();
			
			for(int i=0;i<20000;i++) {
				stat.addBatch("insert into user_info (uesrname,userage) values ('wang"+i+"',21)");
			}
			
			stat.executeBatch();//执行批处理
			connection.commit();
			long end = System.currentTimeMillis();
			
			System.out.println("插入两万条数据耗时："+(end-star));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stat != null) {
					stat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
