package cn.e3mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * @Date 2018/4/14 23:32
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.portal.controller
 * @ClassName: IndexController
 * @Description:
 *
 */
@Controller
public class IndexController {
	
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	
	@Autowired
	private ContentService contentService;

	/**
	 * @Date 2018/4/14 23:32
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: showIndex
	 * @Params: [model]
	 * @ReturnType: java.lang.String
	 * @Description:
	 *
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		/**查询内容列表*/
		List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
		/**把结果传递给页面*/
		model.addAttribute("ad1List", ad1List);
		return "index";
	}
}
