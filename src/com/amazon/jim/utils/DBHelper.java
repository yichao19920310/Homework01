//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : DBHelper.java
//  @ Date : 2017/12/11
//  @ Author : 
//
//



package com.amazon.jim.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper{
	public static final String DRIVER = "oracle.jdbc.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public static final String USER = "myAmazon";
	public static final String PWD = "1234";

	Connection mConnection;

	/**
	 * ���غ����ݿ�����Ӷ���.
	 * 
	 * @return
	 */
	public Connection getConnection() {
		if (mConnection == null) {
			try {
				// --Ϊ�˼������.�ǿ��Բ�д��.Ŀ���Ǽ�������.
				Class.forName(DRIVER);
				mConnection = DriverManager.getConnection(URL, USER, PWD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mConnection;
	
	}
/**
 * �ر�����
 */
	public void closeConnection(Connection conn) {
		if(conn == null) {
			
		}
	} 
}
