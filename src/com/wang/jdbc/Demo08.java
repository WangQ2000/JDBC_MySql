package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * JDBC连接数据库+执行sql语句+批处理 取出指定时间段的数据
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo08 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "select * from user_info where lastLogin > ? and lastLogin<? order by lastLogin";
			java.sql.Timestamp sdate = new java.sql.Timestamp(strDateToTime("2020-08-04 22:23:45"));
			java.sql.Timestamp edate = new java.sql.Timestamp(strDateToTime("2020-08-10 22:23:45"));
			pstat = connection.prepareStatement(sql);
			pstat.setObject(1, sdate);
			pstat.setObject(2, edate);
			resultSet = pstat.executeQuery();
			System.out.println(resultSet.wasNull());
			while(resultSet.next()) {
				System.out.println(resultSet.getObject(1)+"-->"+resultSet.getObject(2)+"-->"+resultSet.getObject(4));
			}
			System.out.println("第一个用户插入结束");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstat != null) {
					pstat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 日期转换为long类型的数 日期格式（yyyy-MM-dd hh:mm:ss)
	 * 
	 */
	// 日期转换为long类型的数
	public static long strDateToTime(String date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}
}
