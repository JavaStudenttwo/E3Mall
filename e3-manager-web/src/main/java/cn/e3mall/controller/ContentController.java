package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * @Date 2018/4/19 22:17
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.controller
 * @ClassName: ContentController
 * @Description: 商品内容管理
 *
 */
@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;

	/**
	 * @Date 2018/4/19 22:17
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: addContent
	 * @Params: [content]
	 * @ReturnType: cn.e3mall.common.utils.E3Result
	 * @Description: 商品内容添加
	 *
	 */
	@RequestMapping(value="/content/save", method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content) {
		E3Result e3Result = contentService.addContent(content);
		return e3Result;
	}
}
