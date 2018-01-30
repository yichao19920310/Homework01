package com.amazon.jim.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.jim.dao.IShopDao;
import com.amazon.jim.entity.Cart;
import com.amazon.jim.entity.OrderDetails;
import com.amazon.jim.entity.OrderSum;
import com.amazon.jim.entity.User;
import com.amazon.jim.utils.JDBCUtils;

public class ShopDaoImpl implements IShopDao {

	@Override
	public List<Cart> showCart(int uid) throws SQLException {
		//获取conn
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="SELECT ID,PID,QUANTITY FROM HWUA_CART WHERE USERID=?";
		//执行使用结果集
		List<Cart> cartList = run.query(sql,new BeanListHandler<>(Cart.class),uid);
//				for (Products products : pList) {
//					System.out.println("res: "+products);
//				}
		
		return cartList;
		

}

	@Override
	public int checkPid(int pId, int userId) throws SQLException {
		//初始化
		int id=0;
		//
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="SELECT ID FROM HWUA_CART WHERE PID=? AND USERID=?";
		Cart myCart=run.query(sql,new BeanHandler<>(Cart.class),pId,userId);
		System.out.println("sdI: 42: 结果集"+myCart);
		//如果结果不为null,返回该记录的id
		if(myCart!=null){
			 id = myCart.getId();
		}
		System.out.println("sdI: 47: 返回"+id);
		return id;
	}
	
	@Override
	public int proInsert(int pId, int quantity, int userId) throws SQLException {
		//获取conn
				QueryRunner run = JDBCUtils.getQueryRunner();
				//预处理
				String sql ="Insert into HWUA_CART values(SEQ_HWUA_CART.nextval,?,?,?)";
				//执行使用结果集
				int result = run.update(sql, pId,quantity,userId);
		return result;
	}

	@Override
	public int proUpdate(int pId, int quantity, int userId) throws SQLException {
		System.out.println("sdI: 65: [pid="+pId+", quantity="+quantity+", userId="+userId);
		//获取conn
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="UPDATE HWUA_CART SET QUANTITY = QUANTITY +? WHERE PID=? AND USERID=?";
		//执行使用结果集
		int result = run.update(sql,quantity,pId,userId);
		
		System.out.println("sdI: 73:"+result);
		return result;
	}

	@Override
	public int generateOrderId(User user) throws SQLException {
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "INSERT INTO "
				+ "HWUA_ORDER"
				+ "(HO_ID, HO_USER_ID, HO_USER_NAME, HO_USER_ADDRESS, HO_CREATE_TIME, HO_COST, HO_STATUS, HO_TYPE) "
				+ "VALUES"
				+ "(SEQ_ORDER.NEXTVAL,?,?,?,to_date(sysdate),0,0,1)";//status 初始化0/未提交
		int row = run.update(sql
				,user.getHu_user_id()
				,user.getHu_user_name()
				,user.getHu_address());
		return row;
	}

	@Override
	public int getOrderIdByUser(User user) throws SQLException {
		//1.获取数据库操作对象run
				QueryRunner run = JDBCUtils.getQueryRunner();
				//2.执行
				String sql = "SELECT HO_ID FROM (SELECT HO_ID,HO_USER_ID,HO_STATUS FROM HWUA_ORDER) where HO_STATUS =0 AND HO_USER_ID=?";
				OrderSum os=run.query(sql, new BeanHandler<>(OrderSum.class), user.getHu_user_id());
				int ho_id=0;
				if(os!=null){
				ho_id=os.getHo_id();}
		return ho_id;
	}
	
	@Override
	public int putOrderDetail(int ho_id, int hp_Id, int hod_quantity, double cost)throws SQLException {
		System.out.println("sdI: 109: [ho_id="+ho_id+", hp_Id="+hp_Id+", hod_quantity="+hod_quantity+", cost="+cost+"]");
		//获取conn
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="INSERT INTO HWUA_ORDER_DETAIL VALUES(SEQ_DETAIL.NEXTVAL,?,?,?,?)";
		//执行使用结果集
		int result = run.update(sql,ho_id,hp_Id,hod_quantity,cost);
		
		System.out.println("sdI: 87:"+result);
		return result;
	}

	@Override
	public int finOrderbyId(int ho_id, double totalCost) throws SQLException {
		System.out.println("sdI: 123: [ho_id="+ho_id+"]");
		//获取conn
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="update HWUA_ORDER SET HO_CREATE_TIME=to_date(sysdate),HO_COST=?,HO_STATUS=1 WHERE HO_ID=? AND HO_STATUS=0";
		//执行使用结果集
		int result=run.update(sql, totalCost,ho_id);
		System.out.println("sdI: 132: [result="+result+"]");
		return result;
	}

	@Override
	public OrderSum getOrderbyId(int ho_id) throws SQLException {
		//获取conn
				QueryRunner run = JDBCUtils.getQueryRunner();
				//预处理
				String sql ="SELECT * FROM HWUA_ORDER WHERE HO_ID=?";
				OrderSum order=run.query(sql, new BeanHandler<>(OrderSum.class), ho_id);
		return order;
	}

	@Override
	public OrderDetails getOrderDetailbyParam(int ho_id, int hp_id) throws SQLException {
		System.out.println("sdI: 109: [ho_id="+ho_id+", hp_Id="+hp_id+"]");
		//获取conn
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="SELECT * FROM HWUA_ORDER_DETAIL WHERE HO_ID=? and HP_ID=?";
		OrderDetails od=run.query(sql, new BeanHandler<>(OrderDetails.class), ho_id,hp_id);
		
		System.out.println(od.toString());
		return od;
	}

	@Override
	public int clearCListById(int id) throws SQLException {
		System.out.println("sdI: 161: [id="+id+"]");
		//获取conn
		QueryRunner run = JDBCUtils.getQueryRunner();
		//预处理
		String sql ="delete from HWUA_CART where ID=?"; 
		int result=run.update(sql, id);
		return result;
	}



}
