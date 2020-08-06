package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC连接数据库
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo01 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";
	public static void main(String[] args) {
		Connection connection = null;
		try {
			//加载驱动类
			Class.forName(CALSSNAME);
			long star = System.currentTimeMillis();
			//建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			long end = System.currentTimeMillis();
			System.out.println(connection);
			System.out.println("建立连接耗时："+(end-star));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!= null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
