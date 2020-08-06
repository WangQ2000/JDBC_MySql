package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC�������ݿ�
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
			//����������
			Class.forName(CALSSNAME);
			long star = System.currentTimeMillis();
			//��������
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			long end = System.currentTimeMillis();
			System.out.println(connection);
			System.out.println("�������Ӻ�ʱ��"+(end-star));

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
