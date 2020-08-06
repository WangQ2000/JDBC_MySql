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
public class Demo03 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement prestat = null;
		ResultSet resultset = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			prestat = connection.prepareStatement("select * from user_info where userage>=?");
			prestat.setObject(1, 20);
			resultset = prestat.executeQuery();
			while (resultset.next()) {
				System.out.print(resultset.getRow()+"-->");
				System.out.println(resultset.getString(1) + "-->" + resultset.getInt(2));
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
