package spittr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.pojo.Spitter;
import spittr.repository.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	private SpitterRepository spitterRepository;

	public SpitterController() {

	}

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "registerForm";
	}

	/**
	 * Errors参数要紧 跟在带有@Valid注解的参数后面
	 * 项目依赖上要有Java API的实现(尽管没有调用~~)，比如Hibernate Validator，不然@valid不生效
	 * @param spitter
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors) {

		if (errors.hasErrors()) {
			return "registerForm";
		}

		spitterRepository.save(spitter);

		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {

		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);

		return "profile";
	}

}
