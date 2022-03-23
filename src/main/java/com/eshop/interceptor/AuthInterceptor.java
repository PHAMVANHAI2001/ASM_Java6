package com.eshop.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class AuthInterceptor implements HandlerInterceptor {
//    @Autowired
//    SessionService session;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String uri = request.getRequestURI();
//        User user = session.get("user");
//        String error = "";
//        if (user == null) {
//            error = "Please login!";
//        }
//        else if (!user.getIsAdmin() && uri.startsWith("/admin/")) {
//            error = "Access denied!";
//        }
//        if (error.length() > 0) { // có lỗi
//            session.set("security-uri", uri);
//            response.sendRedirect("/user/login?error=" + error);
//            return false;
//        }
//        return true;
//    }


}
