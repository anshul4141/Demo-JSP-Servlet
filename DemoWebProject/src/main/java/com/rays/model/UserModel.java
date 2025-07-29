package com.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.rays.bean.UserBean;

public class UserModel {

	public static Connection getConnection() throws Exception {

		ResourceBundle rb = ResourceBundle.getBundle("com.rays.bundle.system");

		String driver = rb.getString("driver");
		String url = rb.getString("url");
		String passwrod = rb.getString("password");
		String username = rb.getString("username");

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, passwrod);

		return conn;

	}

	public int nextPk() {
		int id = 0;
		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from user");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id + 1;

	}

	public void add(UserBean bean) {

		Connection conn = null;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			throw new RuntimeException("login already exists");
		}

		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into user values(?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));

			int i = pstmt.executeUpdate();
			System.out.println("data inserted successfully: " + i);

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(UserBean bean) {

		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update user set firstName = ?, lastName = ?, login = ?, password = ?, dob = ? where id = ?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setInt(6, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println("data updated successfully: " + i);

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(UserBean bean) {

		Connection conn = null;

		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from user where id = ?");
			pstmt.setInt(1, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println("data deleted successfully: " + i);

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<UserBean> search(UserBean bean) throws Exception {

		Connection conn = getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user");

		ResultSet rs = pstmt.executeQuery();

		List<UserBean> list = new ArrayList<UserBean>();

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			list.add(bean);
		}

		pstmt.close();
		conn.close();
		return list;

	}

	public UserBean findByPk(int id) throws Exception {

		Connection conn = getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
		}

		pstmt.close();
		conn.close();
		return bean;

	}

	public UserBean authenticate(String login, String password) throws Exception {

		Connection conn = getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user where login = ? and password = ?");

		pstmt.setString(1, login);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
		}

		pstmt.close();
		conn.close();
		return bean;

	}

	public UserBean findByLogin(String login) {
		Connection conn = null;
		UserBean bean = null;
		try {
			conn = getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from user where login = ?");

			pstmt.setString(1, login);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
			}

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;

	}

}
