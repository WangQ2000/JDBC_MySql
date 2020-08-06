package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC�������ݿ�+ִ��sql���+������ 
 * Batch
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo05 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		Statement stat = null;
		try {
			// ����������
			Class.forName(CALSSNAME);
			// ��������
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			connection.setAutoCommit(false);//�����ֶ��ύ
			long star  = System.currentTimeMillis();
			stat = connection.createStatement();
			
			for(int i=0;i<20000;i++) {
				stat.addBatch("insert into user_info (uesrname,userage) values ('wang"+i+"',21)");
			}
			
			stat.executeBatch();//ִ��������
			connection.commit();
			long end = System.currentTimeMillis();
			
			System.out.println("�������������ݺ�ʱ��"+(end-star));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
