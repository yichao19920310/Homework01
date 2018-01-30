package com.amazon.jim.service;

import java.util.List;
import java.util.Map;

import com.amazon.jim.entity.Categories;

public interface ICateService {

	Map<Categories,List<Categories>> getCategories();

}
