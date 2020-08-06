package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;


/**
 * JDBC连接数据库+执行sql语句+批处理 
 * 	插入随机时间
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo07 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstat = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "insert into user_info (username,userage,joinDate,lastLogin) values (?,?,?,?)";
			pstat = connection.prepareStatement(sql);
			for(int i =0;i<1000;i++) {
				pstat.setObject(1, "wang"+i);
				pstat.setObject(2, 20);
				int rand = 100000000+new Random().nextInt(1000000000);
				java.sql.Date date = new java.sql.Date(System.currentTimeMillis()-rand);
				pstat.setDate(3, date);
				java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis()-rand);
				pstat.setTimestamp(4, timestamp);
				pstat.execute();
			}
			System.out.println("第一个用户插入结束");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstat != null) {
					pstat.close();
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
