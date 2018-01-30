package com.amazon.jim.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.jim.dao.INewsDao;
import com.amazon.jim.entity.News;
import com.amazon.jim.utils.JDBCUtils;

public class NewsDaoImpl implements INewsDao {
	//showNews
	@Override
	public List<News> getRecentNews() throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_NEWS";
		List<News> nList = run.query(sql, new BeanListHandler<>(News.class));
		
//		for (News products : nList) {
//		System.out.println("res: "+products);
//	}
		return nList;
	}

	@Override
	public News getNews(int nid) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_NEWS where HN_ID=?";
		News n = run.query(sql, new BeanHandler<>(News.class),nid);
		return n;
	}
}
