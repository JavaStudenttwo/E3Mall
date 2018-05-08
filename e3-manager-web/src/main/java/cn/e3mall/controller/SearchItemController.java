package cn.e3mall.controller;

import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.service.SearchItemService;

import javax.annotation.Resource;


/** 
 * @Date 2018/4/17 16:08
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.controller
 * @ClassName: SearchItemController 
 * @Description: 
 *
 */
@Controller
public class SearchItemController {

	@Resource(name = "itemService")
	private ItemService itemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemList() {
		E3Result e3Result = itemService.importAllItems();
		return e3Result;
		
	}
}
