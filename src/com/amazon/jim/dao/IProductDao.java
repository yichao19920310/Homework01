package com.amazon.jim.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.jim.entity.Products;

public interface IProductDao {
	
	List<Products> getHotProduct()throws SQLException;
	

	int queryCount()throws SQLException;
	
	List<Products> getProducts(int start, int end)throws SQLException;
	
	
	int queryCount(String cate, int hpcId)throws SQLException;	

	List<Products> getProducts(String cate, int hpcId, int start, int end)throws SQLException;

	

	int queryCount(String hpName)throws SQLException;

	List<Products> getProducts(String hpName, int start, int end)throws SQLException;

	

	int queryCount(int pId)throws SQLException;

	List<Products> getProducts(int pId, int start, int end)throws SQLException;

	Products getProduct(int pId)throws SQLException;

	

}
