package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @Date 2017/12/17 18:52
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.controller
 * @ClassName: PageController 
 * @Description: 
 *
 */
@Controller
public class PageController {

	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
