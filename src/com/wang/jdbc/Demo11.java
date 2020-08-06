package com.wang.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import com.mysql.jdbc.Blob;

/**
 * JDBC连接数据库+Blob
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo11 {

	static Properties pros = null;
	static {
		pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			//pros.getProperty("");
			connection = JDBCUtil.getConnection();
			// 读取图片信息
			pstat = connection.prepareStatement("select * from user_info");
			resultSet = pstat.executeQuery();
			while (resultSet.next()) {
				Blob blob = (Blob) resultSet.getBlob("photo");
				is = blob.getBinaryStream();
				os = new FileOutputStream("title.jpg");
				int temp = 0;
				while ((temp = is.read()) != -1) {
					os.write(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			JDBCUtil.close(resultSet, pstat, connection);
		}
	}
}
