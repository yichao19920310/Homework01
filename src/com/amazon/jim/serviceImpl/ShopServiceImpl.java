package com.amazon.jim.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.jim.dao.IShopDao;
import com.amazon.jim.daoImpl.ProductDaoImpl;
import com.amazon.jim.daoImpl.ShopDaoImpl;
import com.amazon.jim.entity.Cart;
import com.amazon.jim.entity.OrderDetails;
import com.amazon.jim.entity.OrderSum;
import com.amazon.jim.entity.User;
import com.amazon.jim.service.IShopService;

public class ShopServiceImpl implements IShopService {
IShopDao isd= new ShopDaoImpl(); 

	@Override
	public List<Cart> showCart(int uid) {
		List<Cart> carTList = null;
		try {
			carTList = isd.showCart(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ssi: 24: car="+carTList.toString());
		if(carTList!=null){
		for (Cart cart : carTList) {
			try {
				cart.setProduct(new ProductDaoImpl().getProduct(cart.getPid()));
				System.out.println("ssi: 29: car="+cart.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}
		return carTList;
	}
	@Override
	public int updateCart(int pId, int quantity, int userId) {
		int id=0;
		try {
			id=isd.checkPid(pId,userId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//返回id  判断  存在与否(存在t/不存在f)
		
		//初始化结果=-1
		int result = -1;
		try {
		//存在 执行更新操作
		if(id!=0){
		System.out.println("ssI: 50: 存在 该条记录  id = "+id);
		result = isd.proUpdate(pId,quantity,userId);
		System.out.println("ssI: 52: 返回 = "+result); 
		//不存在执行插入操作
		}else {
		System.out.println("ssI: 55: 不存在该记录 id="+id);
		result = isd.proInsert(pId,quantity,userId);
		System.out.println("ssI: 57: 返回 = "+result);
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public OrderDetails getOrderDetail(int ho_id, int hp_Id, int hod_quantity, double cost) {
		OrderDetails orderD = null;
		int result=-1;
		try {
			result=isd.putOrderDetail(ho_id,hp_Id,hod_quantity,cost);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result==1){
			try {
				orderD=isd.getOrderDetailbyParam(ho_id,hp_Id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orderD;
	}
	@Override
	public int genOrderId(User user) {
		System.out.println("ssi:82: genOrderId");
		int result = -1;
		int ho_id = 0;
		try {
			ho_id=getOrderIdByUser(user);
			if(ho_id==0){//不存在订单
			result = isd.generateOrderId(user);}
			else{ho_id=getOrderIdByUser(user);}
		}catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
		}
		if (result != 1) {
			System.out.println("生成失败");
		} else {
			ho_id=getOrderIdByUser(user);
		}
		return ho_id;


	}
	private int getOrderIdByUser(User user) {
		int ho_id1=0;
		try {
			ho_id1=isd.getOrderIdByUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ho_id1;
	}
	@Override
	public int finOrderbyId(int ho_id,double totalCost) {
		int result=-1;
		try {
			result=isd.finOrderbyId(ho_id,totalCost);
		System.out.println("ssi:129: result= "+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public OrderSum getOrderbyId(int ho_id) {
		OrderSum order = null;
		try {
			order = isd.getOrderbyId(ho_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	@Override
	public int clearClistById(int id) {
		int result=-1;
		try {
			result=isd.clearCListById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
//		
		
	}

}
