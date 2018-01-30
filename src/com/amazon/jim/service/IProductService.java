package com.amazon.jim.service;

import java.util.List;

import com.amazon.jim.entity.Pager;
import com.amazon.jim.entity.Products;

public interface IProductService {

	List<Products> getPList(Pager pa);

	List<Products> getPList(String cate, int hpcId, Pager pa);

	List<Products> getPList(String hpName, Pager pa);

	List<Products> getHotProduct();

	List<Products> getPList(int pId, Pager pa);

	Products getProduct(int pId);

}
