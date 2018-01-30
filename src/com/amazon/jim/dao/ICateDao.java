package com.amazon.jim.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.amazon.jim.entity.Categories;

public interface ICateDao {

	Map<Categories,List<Categories>> getCate()throws SQLException;

}
