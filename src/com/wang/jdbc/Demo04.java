package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC连接数据库+执行sql语句 
 * PreparedStatement
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo04 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement prestat = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			prestat = connection.prepareStatement("insert into user_info (uesrname,userage) values (?,?)");
			prestat.setObject(1, "chen");
			prestat.setObject(2, 22);
			int result = prestat.executeUpdate();
			System.out.println("插入"+result+"行");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(prestat != null) {
					prestat.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
