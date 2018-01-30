package com.amazon.jim.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.jim.dao.INewsDao;
import com.amazon.jim.daoImpl.NewsDaoImpl;
import com.amazon.jim.entity.News;
import com.amazon.jim.service.INewsService;

public class NewsServiceImpl implements INewsService {
	INewsDao ind = new NewsDaoImpl();
	//shownews
	@Override
	public List<News> showNews() {
		List<News> nList=null;
		try {
			nList =ind.getRecentNews();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nList;
	}
	@Override
	public News readNews(int nid) {
		News n = null;
		try {
			n = ind.getNews(nid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
}
