package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC连接数据库+执行sql语句 
 * Statement
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo02 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		Statement stat = null;
		ResultSet resultset = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			stat = connection.createStatement();

			resultset = stat.executeQuery("select * from user_info");
			while (resultset.next()) {
				System.out.println(resultset.getString(1) + "\t:\t" + resultset.getInt(2));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultset != null) {
					resultset.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
