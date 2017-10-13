package com.gec.dao;

public class DaoFactory {

	public static UserDao getUserDao ()
	{
		return new  UserDao();
	}
	
	public static ApartDao getApartDao ()
	{
		return new  ApartDao();
	}
	
	public static JobDao getJobDao ()
	{
		return new  JobDao();
	}
	
	public static EmployeeDao getEmpDao ()
	{
		return new  EmployeeDao();
	}
	
	public static FileDao getFileDao()
	{
		return new FileDao();
	}
	
}
