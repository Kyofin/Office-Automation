package com.gec.db;

import java.io.IOException;
import java.io.Reader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class DataSourceUtil {
	private static DruidDataSource dataSource = null;
	
	//配置数据源
	static
	{
		Context iContext;
		try {
			iContext = new InitialContext();
			dataSource= (DruidDataSource) iContext.lookup("java:comp/env/jdbc/MysqlOasystemDataSource");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	//通过mybatis总配置文件回去sqlsession对象
	public static SqlSession getSqlSession()
	{
		try {
			Reader reader = Resources.getResourceAsReader("com/imooc/config/Configuration.xml");
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			return sessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取数据源
	public static DruidDataSource  getDataBase()
	{
		return dataSource;
	}
	
}
