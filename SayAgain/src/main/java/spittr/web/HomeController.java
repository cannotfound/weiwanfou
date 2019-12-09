package spittr.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.pojo.SpitterUser;
import spittr.service.RabbitMQService;

@Controller
@RequestMapping({ "/", "/homepage" })
public class HomeController{

	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private RabbitMQService rabbitMQService;

	@RequestMapping(method = RequestMethod.GET)
	public String home() {

		logger.error("---------homepage--------");
		logger.info("---------homepage info--------");

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login2(Model model, HttpSession session, @Valid SpitterUser user, Errors error) {

		//HttpSession session = request.getSession();
		
		String username = user.getUsername();
		String password = user.getPassword();

		if(username.equals("admin") && password.equals("admin")) {
			
			session.setAttribute("user", user);
			
			
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	        Subject currentUser = SecurityUtils.getSubject();
	        currentUser.login(token); 
			
			//如果放在AOP实现？？不在这里执行
			//this.rabbitMQService.login();	
			
			
			//this.rabbitMQService.receive();
			
			return "redirect:spitter/JackMa";
		}
		
		model.addAttribute("errorMsg", "用户名或者密码错。");
		return "login";
		

	}
}
