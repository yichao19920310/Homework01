package com.amazon.jim.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.jim.dao.IProductDao;
import com.amazon.jim.entity.Products;
import com.amazon.jim.utils.JDBCUtils;

public class ProductDaoImpl implements IProductDao {

	
	@Override
	public int queryCount() throws SQLException {
		int count = -1;
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}
	
	@Override
	public List<Products> getProducts(int start, int end) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT) T WHERE T.R>? AND T.R<=?";
		List<Products> pList = run.query(sql, 
				new BeanListHandler<>(Products.class),start,end);
		return pList;
	}
	
	@Override
	public int queryCount(String cate, int hpcId) throws SQLException {
		int count = -1;
		Connection conn = JDBCUtils.getConnection();
		String sql="";
		if(cate.equals("parent")) {
			sql = "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT WHERE HPC_ID=?";
		}else if(cate.equals("child")) {
			sql = "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT WHERE HPC_CHILD_ID=?";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, hpcId);

//		System.out.println("hpcId: "+hpcId);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
//		System.out.println(count);
		return count;
	}
	
	@Override
	public List<Products> getProducts(String cate, int hpcId, int start, int end) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		
		String sql ="";
		if(cate.equals("parent")) {
			sql="SELECT * FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HPC_ID=?) T WHERE T.R>? AND T.R<=?";
		}else if(cate.equals("child")) {
			sql="SELECT * FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HPC_CHILD_ID=?) T WHERE T.R>? AND T.R<=?";
		}
		List<Products> pList = run.query(sql,new BeanListHandler<>(Products.class),hpcId,start,end);
		return pList;
	}

	@Override
	public int queryCount(String hpName) throws SQLException {
		int count = -1;
		Connection conn = JDBCUtils.getConnection();
		String sql= "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT WHERE HP_NAME LIKE '%'||?||'%'";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, hpName);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
//		System.out.println(count);
		return count;
	}

	@Override
	public List<Products> getProducts(String hpName, int start, int end) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		
		String sql ="SELECT * FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HP_NAME LIKE '%'||?||'%') T WHERE T.R>? AND T.R<=?";
		
		List<Products> pList = run.query(sql,new BeanListHandler<>(Products.class),hpName,start,end);
//		for (Products products : pList) {
//			System.out.println("res: "+products);
//		}
		return pList;
	}

	@Override
	public int queryCount(int pId) throws SQLException {
		int count = -1;
		Connection conn = JDBCUtils.getConnection();
		String sql= "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT WHERE HP_ID =?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, pId);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
//		System.out.println(count);
		return count;
	}

	@Override
	public List<Products> getProducts(int pId, int start, int end) throws SQLException {
		//获取conn
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="SELECT * FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HP_ID =?) T WHERE T.R>? AND T.R<=?";
		//执行使用结果集
		List<Products> p = run.query(sql,new BeanListHandler<>(Products.class),pId,start,end);
//		for (Products products : pList) {
//			System.out.println("res: "+products);
//		}
		return p;
	}

	@Override
	public List<Products> getHotProduct() throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT ORDER BY HP_STOCK DESC) T WHERE T.R>0 AND T.R<=4 ";
		List<Products> hotProduct = run.query(sql, new BeanListHandler<>(Products.class));
		
//		for (News products : nList) {
//		System.out.println("res: "+products);
//	}
		return hotProduct;
	}

	@Override
	public Products getProduct(int pId) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT HP_ID,HP_NAME,HP_DESCRIPTION,HP_PRICE,HP_STOCK,HPC_ID,HPC_CHILD_ID,HP_FILE_NAME FROM HWUA_PRODUCT WHERE HP_ID=?";
		Products pro=run.query(sql, new BeanHandler<>(Products.class),pId);
		return pro;
	}


}
