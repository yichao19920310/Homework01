package com.amazon.jim.service;

import java.util.List;

import com.amazon.jim.entity.Cart;
import com.amazon.jim.entity.OrderDetails;
import com.amazon.jim.entity.OrderSum;
import com.amazon.jim.entity.User;

public interface IShopService {

	List<Cart> showCart(int uid);

	int updateCart(int pId, int quantity, int userId);

	

	int genOrderId(User user);

	OrderDetails getOrderDetail(int ho_id, int hp_Id, int hod_quantity, double cost);

	int finOrderbyId(int ho_id, double totalCost);

	OrderSum getOrderbyId(int ho_id);

	int clearClistById(int id);



}
