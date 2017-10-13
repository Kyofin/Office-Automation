package com.gec.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.gec.db.DataSourceUtil;

public class BaseDao {

	public Connection getConnection()
	{
		Connection conn = null;
		try {
			conn = DataSourceUtil.getDataBase().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn)
	{
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
