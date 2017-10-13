package com.gec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gec.Interfdao.IApartDao;
import com.gec.bean.Apart;
import com.gec.bean.PageModle;
import com.gec.bean.User;

public class ApartDao extends BaseDao implements IApartDao{

	
	
	
	@Override
	public boolean addApart(Apart apart) 
	{

		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("insert into dept_inf(name,remark) values(?,?)");
			
			//填入参数
			prstm.setString(1, apart.getName());
			prstm.setString(2, apart.getRemark());
		
			
			//执行语句
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("添加成功"+pos+"条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;
	
	}

	@Override
	public boolean deleteApart(Apart apart) {
		String strid =String.valueOf(apart.getId());
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("delete from dept_inf where ID = ?");
			
			//填入参数
			prstm.setInt(1, Integer.valueOf(strid));
			
			//执行语句
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("删除成功"+pos+"条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;	
	
	}

	@Override
	public boolean updateApart(Apart apart) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public int getTotalNum() {
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select count(*) from dept_inf ");
			ResultSet resultSet= prstm.executeQuery();
			while(resultSet.next())
			{
				return resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return 0;
	}
	
	@Override
	public List<Apart> getApartListByPage(PageModle pageModle)
	{

		List<Apart> aprtList = new ArrayList<>();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			
			//设置总的记录数量
			pageModle.setTotalRows(getTotalNum());
			int pageStartRow = pageModle.getStartRow();
			
			String sql = "select NAME,REMARK,ID from dept_inf limit "+pageStartRow+","+pageModle.getPageSize();
			
			prstm = conn.prepareStatement(sql);
			ResultSet resultSet= prstm.executeQuery();
			while(resultSet.next())
			{
				Apart apart = new Apart();
				apart.setName(resultSet.getString(1));
				apart.setRemark(resultSet.getString(2));
				apart.setId(resultSet.getInt(3));
				aprtList.add(apart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return aprtList;
	
	}

	

	@Override
	public Apart getApart(Apart apart) 
	{
		Apart outApart = new  Apart();
		Connection conn=null;
		PreparedStatement pre =null;
		conn=getConnection();
		try {
			pre = conn.prepareStatement("select * from dept_inf where id =? ");
			pre.setInt(1, apart.getId());
			ResultSet resultSet = pre.executeQuery();
			while(resultSet.next())
			{
				outApart.setId(resultSet.getInt(1));
				outApart.setName(resultSet.getString(2));
				outApart.setRemark(resultSet.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return outApart;
	}

	@Override
	public List<Apart> queryApartListByLike(String apartname) {

		List<Apart> aprtList = new ArrayList<>();
		Connection conn=null;
		Statement stm = null;
		try {
			StringBuffer sqlBuf = new StringBuffer("select NAME,REMARK,ID from dept_inf where 1=1 ");
			conn = getConnection();
			stm = conn.createStatement();
			
			if (!apartname.equals(""))
			{
				sqlBuf.append("and name like '%"+apartname+"%'");
			}
			
			ResultSet resultSet= stm.executeQuery(sqlBuf.toString());
			while(resultSet.next())
			{
				Apart apart = new Apart();
				apart.setName(resultSet.getString(1));
				apart.setRemark(resultSet.getString(2));
				apart.setId(resultSet.getInt(3));
				aprtList.add(apart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return aprtList;
	
		
	}

	@Override
	public List<Apart> getApartList() 
	{


		List<Apart> aprtList = new ArrayList<>();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
		
			String sql = "select NAME,REMARK,ID from dept_inf ";
			
			prstm = conn.prepareStatement(sql);
			ResultSet resultSet= prstm.executeQuery();
			while(resultSet.next())
			{
				Apart apart = new Apart();
				apart.setName(resultSet.getString(1));
				apart.setRemark(resultSet.getString(2));
				apart.setId(resultSet.getInt(3));
				aprtList.add(apart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return aprtList;
	
	
	}

}
