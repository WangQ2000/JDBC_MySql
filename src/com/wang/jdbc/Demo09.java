package com.wang.jdbc;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Clob;

/**
 * JDBC连接数据库+Clob
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo09 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		Reader reader = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

//			pstat = connection.prepareStatement("insert into user_info (username,myInfo) values(?,?)");
//			pstat.setObject(1, "wangqing02");
//			插入文件内的数据
//			pstat.setClob(2, new FileReader(new File("E:/myjava/a.txt")));
//			插入字符串
//			pstat.setClob(2, new InputStreamReader(new
//			ByteArrayInputStream("aaaaa".getBytes())));
//
//			int rows = pstat.executeUpdate();
//			 取出数据
//			System.out.println("插入"+rows+"行");
			pstat = connection.prepareStatement("select * from user_info");
			resultSet = pstat.executeQuery();
			while (resultSet.next()) {
				Clob clob = (Clob) resultSet.getClob("myInfo");
				reader = clob.getCharacterStream();
				int temp = 0;
				while ((temp = reader.read()) != -1) {
					System.out.print((char) temp);
				}
				System.out.println();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
