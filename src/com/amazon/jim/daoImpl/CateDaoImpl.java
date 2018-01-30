package com.amazon.jim.daoImpl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.jim.dao.ICateDao;
import com.amazon.jim.entity.Categories;
import com.amazon.jim.utils.JDBCUtils;

public class CateDaoImpl implements ICateDao {

	@Override
	public Map<Categories,List<Categories>> getCate() throws SQLException {
	
		Map<Categories,List<Categories>> cMap=new LinkedHashMap<Categories,List<Categories>>();
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql ="SELECT HPC_ID,HPC_NAME,HPC_PARENT_ID FROM HWUA_PRODUCT_CATEGORY WHERE HPC_ID = HPC_PARENT_ID ORDER BY HPC_ID";
		List<Categories> cList= run.query(sql, new BeanListHandler<>(Categories.class));
		
		for (Categories categories : cList) {
			int P_id=categories.getHpc_id();
			String sql2 ="SELECT HPC_ID,HPC_NAME,HPC_PARENT_ID FROM HWUA_PRODUCT_CATEGORY WHERE NOT HPC_ID = HPC_PARENT_ID AND HPC_PARENT_ID=?ORDER BY HPC_PARENT_ID";
			List<Categories> ccList= run.query(sql2, new BeanListHandler<>(Categories.class),P_id);
		cMap.put(categories, ccList);
//		System.out.println("ccList:"+ccList);
		}
		return cMap;
	}

}
