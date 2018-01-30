package com.amazon.jim.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.jim.entity.Cart;
import com.amazon.jim.entity.OrderDetails;
import com.amazon.jim.entity.OrderSum;
import com.amazon.jim.entity.User;

public interface IShopDao {

	List<Cart> showCart(int uid)throws SQLException;

	int proInsert(int pId, int quantity, int userId) throws SQLException;

	int proUpdate(int pId, int quantity, int userId)throws SQLException;

	int checkPid(int pId, int userId) throws SQLException;

	int putOrderDetail(int ho_id, int hp_Id, int hod_quantity, double cost)throws SQLException;

	int generateOrderId(User user)throws SQLException;

	int getOrderIdByUser(User user)throws SQLException;

	int finOrderbyId(int ho_id, double totalCost)throws SQLException;

	OrderSum getOrderbyId(int ho_id)throws SQLException;

	OrderDetails getOrderDetailbyParam(int ho_id, int hp_id)throws SQLException;

	int clearCListById(int id)throws SQLException;

	
}
