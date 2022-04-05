package com.eshop.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.entities.Cart;
import com.eshop.entities.User;
import com.eshop.jpaRepository.CartRepository;
import com.eshop.jpaRepository.CategoryRepository;
import com.eshop.jpaRepository.ProductRepository;
import com.eshop.service.CartService;
import com.eshop.service.CategoryService;
import com.eshop.service.SessionService;
import com.eshop.service.UserService;

@Service
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	SessionService sessionService;
	@Autowired
    UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CartService cartService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("uri", request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		request.setAttribute("categories", categoryService.findAll());
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		if(username != null) {
//			User user = userService.findByUsername(username);
//			List<Cart> countCart = cartService.findByUserId(user.getId());
//			request.setAttribute("countCart", countCart.size());
//		}
	}
}
