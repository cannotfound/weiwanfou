package spittr.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.pojo.Spittle;
import spittr.repository.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	/**
	 * Model实际上就是一个Map（也就是key-value对的集合），它会传递给视图，这样数据
	 * 就能渲染到客户端了。当调用addAttribute()方法并且不指定key的时候，那么key会根据
	 * 值的对象类型推断确定。在本例中，因为它是一个List<Spittle>，因此，键将会推断 为spittleList。
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {

		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		return "spittles";
	}

	/**
	 * 并没有返回视图名称，也没有显式地设定模型，这个方 法返回的是Spittle列表。当处理器方法像这样返回对象或集合时，这个值会放到模型
	 * 而逻辑视图的名称将会根据请求路径推断得出。因为这个方法处理针对“/spittles”的GET请 求，因此视图的名称将会是spittles（去掉开头的斜线）
	 * 
	 * @return
	 */
	@RequestMapping(path="b", method = RequestMethod.GET)
	public String spittles2(Model model, @RequestParam(value="max", defaultValue="2") long max, @RequestParam(value="count", defaultValue="10") int count) {
		
		model.addAttribute(spittleRepository.findSpittles(max, count));
		
		return "spittles";
	}
	
	@RequestMapping(path="/show/{spittleId}", method=RequestMethod.GET)
	public String spittle(Model model, @PathVariable("spittleId")long id) {
		
		model.addAttribute(spittleRepository.findOne(id));
		
		return "spittle";
	}
}
