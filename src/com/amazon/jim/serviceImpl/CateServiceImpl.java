package com.amazon.jim.serviceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.amazon.jim.dao.ICateDao;
import com.amazon.jim.daoImpl.CateDaoImpl;
import com.amazon.jim.entity.Categories;
import com.amazon.jim.service.ICateService;

public class CateServiceImpl implements ICateService {
	ICateDao icd = new CateDaoImpl();
	@Override
	public Map<Categories,List<Categories>> getCategories() {

		System.out.println("111");
		Map<Categories,List<Categories>> cMap=null;
		try {
			cMap = icd.getCate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cMap;
	}

}
