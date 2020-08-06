package com.wang.jdbc;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.jdbc.Blob;

/**
 * JDBC连接数据库+Blob
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo10 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			// 加载驱动类
			Class.forName(CALSSNAME);
			// 建立连接
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			//插入图片
			/*pstat = connection.prepareStatement("insert into user_info (username,photo) values (?,?)");
			pstat.setString(1, "wangqing");
			pstat.setBlob(2, new FileInputStream("E:/myjava/title.jpg"));
			pstat.executeUpdate();*/
			
			//读取图片信息
			pstat = connection.prepareStatement("select * from user_info");
			resultSet = pstat.executeQuery();
			while(resultSet.next()) {
				Blob blob = (Blob) resultSet.getBlob("photo");
				is = blob.getBinaryStream();
				os = new FileOutputStream("title.jpg");
				int temp = 0;
				while((temp=is.read())!=-1) {
					os.write(temp);
				}
				
			}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
}
