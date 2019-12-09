package spittr.web;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.bouncycastle.math.raw.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.exception.HandsomeNotFoundException;
import spittr.exception.MoneyNotFoundException;
import spittr.pojo.Spitter;
import spittr.repository.SpitterRepository;
import spittr.repository.SpitterRepositoryImpl;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	Logger logger = LoggerFactory.getLogger(SpitterController.class);

	private SpitterRepository spitterRepository;

	public SpitterController() {

	}

	@Autowired
	private SpitterRepositoryImpl spitterRepositoryImpl;
	
	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {

		model.addAttribute(new Spitter());

		/*
		 * 异常处理的测试 if (1 == 2) { throw new MoneyNotFoundException(); } if (1 == 1)
		 * {throw new HandsomeNotFoundException();}
		 */

		return "registerForm";
	}

	/**
	 * Errors参数要紧 跟在带有@Valid注解的参数后面 项目依赖上要有Java API的实现(尽管没有调用~~)，比如Hibernate
	 * Validator，不然@valid不生效
	 * 
	 * @param spitter
	 * @param errors
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@RequestPart("stPicture") MultipartFile stPicture, @Valid Spitter spitter,
			Errors errors, RedirectAttributes model) throws IllegalStateException, IOException {

		if (errors.hasErrors()) {
			return "registerForm";
		}
		String originalFilename = stPicture.getOriginalFilename();
		long size = stPicture.getSize();
		logger.error(" 上传 -----originalFilename=" + originalFilename + "; size=" + size);
		stPicture.transferTo(new File("D://iuiuhih.png"));

		// 这里没有ID设一个，
		spitter.setId(119L);

		// ①---username 直接拼接在重定向的string上
		// spitterRepository.save(spitter);
		// return "redirect:/spitter/" + spitter.getUsername();

		// ②--username使用占位符构建重定向（不安全字符会被转义）
		// model中另一个spitterId属性由于没有占位符与之匹配，所以它会自动加作为参数加在URL后面?spitterId=xx
		// spitterRepository.save(spitter);
		// model.addAttribute("username", spitter.getUsername());
		// model.addAttribute("spitterId", spitter.getId());
		// return "redirect:/spitter/{username}";

		// ③---实现了flash属性的接口model： RedirectAttributes(它包含了Model的所有功能)
		spitterRepository.save(spitter);
		model.addAttribute("username", spitter.getUsername());
		model.addFlashAttribute("spitter", spitter);
		return "redirect:/spitter/{username}";

	}

	@RequestMapping(value = "/registerpart", method = RequestMethod.POST)
	public String processRegistration2(@RequestPart("stPicture") Part stPicture, @Valid Spitter spitter, Errors errors)
			throws IllegalStateException, IOException {

		
		
		if (errors.hasErrors()) {
			return "registerForm";
		}

		String originalFilename = stPicture.getSubmittedFileName();
		long size = stPicture.getSize();
		logger.error(" 上传registerpart -----originalFilename=" + originalFilename + "; size=" + size);
		stPicture.write("D://iuiuhih.png");

		spitterRepository.save(spitter);

		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	//@RequiresPermissions("admin")
	@RequiresRoles("admin")
	public String showSpitterProfile(@PathVariable String username, Model model) {

		if (!model.containsAttribute("spitter")) {

			Spitter spitter = spitterRepository.findByUsername(username);
			model.addAttribute(spitter);

			logger.error("showSpitterProfile()  model中没有 spitter, 用Repository查找出来----");
			
			
			/**
			 * 利用spring事务管理，把sub,add两个动作整成完整一个事务。配置见applicationContext.xml
			 */
			System.out.println("-------start acc--------");
			spitterRepository.subAcc((long) 10, 20);
			

			
			spitterRepository.addAcc((long) 9, 20);
			System.out.println("-------end acc--------");			
			
		}else {
		
			logger.error("showSpitterProfile()  model中有 spitter, 直接展现--------");
		}
		
		return "profile";
	}
	
	@RequestMapping(value = "/jackcache", method = RequestMethod.GET)
	public String jackMaSaveToCache(Model model) {
		
		Spitter cell = spitterRepository.findById(10L);
		
		
		model.addAttribute(cell);
		
		return "profile";
		
	}
	
	@RequestMapping(value = "/byid/{id}", method = RequestMethod.GET)
	public String showSpitterById(@PathVariable Long id, Model model) {
		
		Spitter temp = spitterRepository.findById(id);
		
		model.addAttribute(temp);
		
		return "profile";
	}
	
	
}
