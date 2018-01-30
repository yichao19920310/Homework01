package com.amazon.jim.daoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.amazon.jim.dao.UserDao;
import com.amazon.jim.entity.User;
import com.amazon.jim.utils.JDBCUtils;


public class UserDaoImpl implements UserDao {

	@Override
	public User queryByName(String name) throws SQLException {
				//1.获取数据库操作对象run
				QueryRunner run = JDBCUtils.getQueryRunner();
				//2.执行
				String sql = "SELECT "
								+ "HU_USER_NAME,"
								+ "HU_EMAIL "
							+ "FROM "
								+ "HWUA_USER "
							+ "WHERE "
								+ "HU_USER_NAME = ?";
				User user = run.query(sql, new BeanHandler<>(User.class),name);
				return user;
			}

	@Override
	public User queryByEmail(String email) throws SQLException {
		//1.获取数据库操作对象run
		QueryRunner run = JDBCUtils.getQueryRunner();
		//2.执行
		String sql = "SELECT "
						+ "HU_USER_NAME,"
						+ "HU_EMAIL "
					+ "FROM "
						+ "HWUA_USER "
					+ "WHERE "
						+ "HU_EMAIL = ?";
		User user = run.query(sql, new BeanHandler<>(User.class),email);
		return user;
	}

	@Override
	public int insert(User user) throws SQLException {
		//1.获取数据库操作对象run
				QueryRunner run = JDBCUtils.getQueryRunner();
				//2.执行
				String sql = "INSERT INTO "
						+ "HWUA_USER("
							+ "HU_USER_ID,"
							+ "HU_USER_NAME,"
							+ "HU_PASSWORD,"
							+ "HU_SEX,"
							+ "HU_BIRTHDAY,"
							+ "HU_IDENTITY_CODE,"
							+ "HU_EMAIL,"
							+ "HU_MOBILE,"
							+ "HU_ADDRESS,"
							+ "HU_STATUS) "
						+ "VALUES"
						+ "(SEQ_USER.NEXTVAL,?,?,?,?,?,?,?,?,1)";
				int row = run.update(sql
						,user.getHu_user_name()
						,user.getHu_password()
						,user.getHu_sex()
						,user.getHu_birthday()
						,user.getHu_identity_code()
						,user.getHu_email()
						,user.getHu_mobile()
						,user.getHu_address());
				return row;
			}

	@Override
	public User queryByNamePwd(User user) throws SQLException {
		//1.获取数据库操作对象run
				QueryRunner run = JDBCUtils.getQueryRunner();
				//2.执行
				String sql = "SELECT "
							+ "HU_USER_ID,"
							+ "HU_USER_NAME,"
							+ "HU_PASSWORD,"
							+ "HU_SEX,"
							+ "HU_BIRTHDAY,"
							+ "HU_IDENTITY_CODE,"
							+ "HU_EMAIL,"
							+ "HU_MOBILE,"
							+ "HU_ADDRESS,"
							+ "HU_STATUS "
						+ "FROM "
							+ "HWUA_USER "
						+ "WHERE "
							+ "HU_USER_NAME = ? AND HU_PASSWORD = ?";
				User result = run.query(sql
						, new BeanHandler<>(User.class)
						,user.getHu_user_name()
						,user.getHu_password());
				return result;
			}

}
