package com.amazon.jim.service;

import com.amazon.jim.entity.User;

public interface IUserService {

	User checkName(String name);

	User checEmail(String email);

	int register(User user);

	User login(User user);

}
