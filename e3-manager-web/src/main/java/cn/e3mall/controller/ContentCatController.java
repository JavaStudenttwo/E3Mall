package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;

/**
 * @Date 2018/4/19 16:13
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.controller
 * @ClassName: ContentCatController
 * @Description: 商品内容分类管理
 *
 */
@Controller
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * @Date 2018/4/19 16:14
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: getContentCatList
	 * @Params: [parentId]
	 * @ReturnType: java.util.List<cn.e3mall.common.pojo.EasyUITreeNode>
	 * @Description: 商品内容分类查询
	 *
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(
			@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}

	/**
	 * @Date 2018/4/19 16:14
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: createContentCategory
	 * @Params: [parentId, name]
	 * @ReturnType: cn.e3mall.common.utils.E3Result
	 * @Description: 商品内容分类添加
	 *
	 */
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public E3Result createContentCategory(Long parentId, String name) {
		E3Result e3Result = contentCategoryService.addContentCategory(parentId, name);
		return e3Result;
	}
	
	
}
