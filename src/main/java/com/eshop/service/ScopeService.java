package com.eshop.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Cung cấp các phương thức đọc/ghi attribute trong các scope
 */
@Service
public class ScopeService {

	@Autowired
	HttpServletRequest request;

	public HttpServletRequest request() {
		return request;
	}

	public HttpSession session() {
		return request().getSession();
	}

	public ServletContext application() {
		return request().getServletContext();
	}

	/**
	 * Tạo attribute trong request scope
	 * 
	 * @param name  tên attribute
	 * @param value giá trị của attribute
	 */
	public void setRequest(String name, Object value) {
		request().setAttribute(name, value);
	}

	/**
	 * Đọc attribute trong request scope
	 * 
	 * @param name tên attribute
	 * @return Giá trị của attribute hoặc null nếu không tồn tại
	 */
	@SuppressWarnings("unchecked")
	public <T> T getRequest(String name) {
		return (T) request().getAttribute(name);
	}

	/**
	 * Xóa attribute trong request scope
	 * 
	 * @param name tên attribute cần xóa
	 */
	public void removeRequest(String name) {
		request().removeAttribute(name);
	}

	/**
	 * Tạo attribute trong session scope
	 * 
	 * @param name  tên attribute
	 * @param value giá trị của attribute
	 */
	public void setSession(String name, Object value) {
		session().setAttribute(name, value);
	}

	/**
	 * Đọc attribute trong session scope
	 * 
	 * @param name tên attribute
	 * @return Giá trị của attribute hoặc null nếu không tồn tại
	 */
	@SuppressWarnings("unchecked")
	public <T> T getSession(String name) {
		return (T) session().getAttribute(name);
	}

	/**
	 * Xóa attribute trong session scope
	 * 
	 * @param name tên attribute cần xóa
	 */
	public void removeSession(String name) {
		session().removeAttribute(name);
	}

	/**
	 * Tạo attribute trong application scope
	 * 
	 * @param name  tên attribute
	 * @param value giá trị của attribute
	 */
	public void setApplication(String name, Object value) {
		application().setAttribute(name, value);
	}

	/**
	 * Đọc attribute trong application scope
	 * 
	 * @param name tên attribute
	 * @return Giá trị của attribute hoặc null nếu không tồn tại
	 */
	@SuppressWarnings("unchecked")
	public <T> T getApplication(String name) {
		return (T) application().getAttribute(name);
	}

	/**
	 * Xóa attribute trong application scope
	 * 
	 * @param name tên attribute cần xóa
	 */
	public void removeApplication(String name) {
		application().removeAttribute(name);
	}
	
}
