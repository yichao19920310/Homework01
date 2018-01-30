package com.amazon.jim.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.jim.entity.News;

public interface INewsDao {

	List<News> getRecentNews()throws SQLException;

	News getNews(int nid)throws SQLException;;

}
