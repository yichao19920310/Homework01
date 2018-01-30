package com.amazon.jim.serviceImpl;

import java.sql.SQLException;

import com.amazon.jim.dao.UserDao;
import com.amazon.jim.daoImpl.UserDaoImpl;
import com.amazon.jim.entity.User;
import com.amazon.jim.service.IUserService;

public class UserServiceImpl implements IUserService {

	UserDao iud = new UserDaoImpl();

	@Override
	public User checkName(String name) {
		User result = null;
		try {
			result = iud.queryByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User checEmail(String email) {
		User result = null;
		try {
			result = iud.queryByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int register(User user) {
		if(user==null){//非空验证
			throw new RuntimeException("参数为空");
		}
		int row = -1;
		try {
			//1判断用户名是否存在
			User result = iud.queryByName(user.getHu_user_name());
			if(result == null){
				//2将用户数据插入数据库
				row = iud.insert(user);
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public User login(User user) {
		if(user==null){//非空验证
			throw new RuntimeException("参数为空");
		}
		//调用dao完成数据的check
		User result = null;
		try {
			result = iud.queryByNamePwd(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
