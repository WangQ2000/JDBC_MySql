package com.wang.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC�������ݿ�+ִ��sql���+������ 
 * 	����
 * 
 * @author wangQ
 *
 * @date 2020-8-6
 */
public class Demo06 {

	private final static String CALSSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/student";
	private final static String USER = "root";
	private final static String PASSWORD = "wang";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstat = null;
		PreparedStatement pstat2 = null;
		try {
			// ����������
			Class.forName(CALSSNAME);
			// ��������
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			connection.setAutoCommit(false);//Ĭ�������Զ��ύ
			String sql = "insert into user_info (uesrname,userage) values (?,?)";
			pstat = connection.prepareStatement(sql);
			pstat.setObject(1, "wang");
			pstat.setObject(2, 23);
			pstat.execute();
			System.out.println("��һ���û��������");
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			pstat2 = connection.prepareStatement(sql);
			pstat2.setObject(1, "zhao");
			pstat2.setObject(2, 22);
			pstat2.execute();
			System.out.println("�������");
			connection.commit();
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
				if(pstat2 != null) {
					pstat2.close();
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
