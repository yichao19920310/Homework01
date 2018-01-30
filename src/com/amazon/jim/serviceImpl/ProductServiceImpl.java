package com.amazon.jim.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.jim.dao.IProductDao;
import com.amazon.jim.daoImpl.ProductDaoImpl;
import com.amazon.jim.entity.Pager;
import com.amazon.jim.entity.Products;
import com.amazon.jim.service.IProductService;

public class ProductServiceImpl implements IProductService {
	IProductDao ipd = new ProductDaoImpl();

	@Override
	public List<Products> getHotProduct() {
		List<Products> hotProduct=null;
		try {
			hotProduct=ipd.getHotProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotProduct;
	}
	
	@Override
	public List<Products> getPList(Pager pa) {
		
		List<Products> pList=null;
		try {
			//3.获取总记录数
			int count = ipd.queryCount();
			pa.setRecordCount(count);//设置总记录数
			//4.获取区间范围start&end
			int start = (pa.getCurrentPage()-1)*Pager.PAGE_RECORD;
			int end = start+Pager.PAGE_RECORD;
			//5.根据区间start&end,hp_id, hpc_id查询区间集合
			pList = ipd.getProducts(start,end);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pList;
	}
	@Override
	public List<Products> getPList(String cate, int hpcId, Pager pa) {
		List<Products> pList=null;
		try {
			//3.获取总记录数
		
			int count = ipd.queryCount(cate,hpcId);
			pa.setRecordCount(count);//设置总记录数
			//4.获取区间范围start&end
			int start = (pa.getCurrentPage()-1)*Pager.PAGE_RECORD;
			int end = start+Pager.PAGE_RECORD;
			//5.根据区间start&end,hp_id, hpc_id查询区间集合
			pList = ipd.getProducts(cate,hpcId,start,end);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pList;
	}
	@Override
	public List<Products> getPList(String hpName, Pager pa) {
		List<Products> pList=null;
		try {
			//3.获取总记录数
		
			int count = ipd.queryCount(hpName);
			pa.setRecordCount(count);//设置总记录数
			//4.获取区间范围start&end
			int start = (pa.getCurrentPage()-1)*Pager.PAGE_RECORD;
			int end = start+Pager.PAGE_RECORD;
			//5.根据区间start&end,hp_id, hpc_id查询区间集合
			pList = ipd.getProducts(hpName,start,end);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pList;
	}

	@Override
	public List<Products> getPList(int pId, Pager pa) {
		List<Products> p=null;
		try {
			//3.获取总记录数
			int count = ipd.queryCount(pId);
			pa.setRecordCount(count);//设置总记录数
			//4.获取区间范围start&end
			int start = (pa.getCurrentPage()-1)*Pager.PAGE_RECORD;
			int end = start+Pager.PAGE_RECORD;
			//5.根据区间start&end,hp_id, hpc_id查询区间集合
			p = ipd.getProducts(pId,start,end);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Products getProduct(int pId) {
		Products pro = null;
		try {
			pro = ipd.getProduct(pId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
}
