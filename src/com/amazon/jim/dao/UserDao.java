package com.amazon.jim.dao;

import java.sql.SQLException;

import com.amazon.jim.entity.User;

public interface UserDao {

	User queryByName(String name)throws SQLException;

	User queryByEmail(String email)throws SQLException;

	int insert(User user)throws SQLException;

	User queryByNamePwd(User user)throws SQLException;

}
