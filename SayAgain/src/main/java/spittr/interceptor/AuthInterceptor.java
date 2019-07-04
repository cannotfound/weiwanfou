package spittr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import spittr.pojo.SpitterUser;

public class AuthInterceptor implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        
        
        HttpSession session = request.getSession();
        
        SpitterUser user = (SpitterUser) session.getAttribute("user");
        
        if(null == user) {
        	
        	
        	logger.error("还没有登陆-------------");
        	request.setAttribute("msg", "您还没有登录，请先登录！");
        	request.getRequestDispatcher("/login").forward(request, response);
        	return false;
        }
        
        
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        logger.error("----处理时间: {}", System.currentTimeMillis() - startTime);
	}



}
