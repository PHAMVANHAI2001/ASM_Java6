package com.eshop.interceptor;

import com.eshop.entities.ShoppingCart;
import com.eshop.entities.User;
import com.eshop.jpaRepository.CategoryDAO;
import com.eshop.jpaRepository.ProductDAO;
import com.eshop.jpaRepository.ShoppingCartDAO;
import com.eshop.jpaRepository.UserRepo;
import com.eshop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	SessionService sessionService;
	@Autowired
    UserRepo userRepo;
	@Autowired
	ShoppingCartDAO cartDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ProductDAO productDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("uri", request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		request.setAttribute("products", productDAO.findAll());
		request.setAttribute("categories", categoryDAO.findAll());
		String username = sessionService.get("username", null);
		if(username != null) {
			User user = userRepo.findByUsername(username);
			List<ShoppingCart> countCart = cartDAO.findByUserId(user.getId());
			request.setAttribute("countCart", countCart.size());
			boolean isAdmin = user.getIsAdmin();
			request.setAttribute("isAdmin", isAdmin);
		}
	}
}
