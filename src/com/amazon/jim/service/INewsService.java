package com.amazon.jim.service;

import java.util.List;

import com.amazon.jim.entity.News;

public interface INewsService {

	List<News> showNews();

	News readNews(int nId);
}
