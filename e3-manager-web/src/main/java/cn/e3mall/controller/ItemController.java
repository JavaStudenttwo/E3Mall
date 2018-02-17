package cn.e3mall.controller;


import cn.e3mall.common.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

import javax.annotation.Resource;

/** 
 * @Date 2017/12/17 18:51
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.controller
 * @ClassName: ItemController 
 * @Description: 
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * @Date 2017/12/17 18:51
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: getItemById
	 * @Params: [itemId]
	 * @ReturnType: cn.e3mall.pojo.TbItem
	 * @Description:
	 *
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	/**
	 * @Date 2017/12/17 18:52
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: getItmeList
	 * @Params: [page, rows]
	 * @ReturnType: cn.e3mall.common.pojo.EasyUIDataGridResult
	 * @Description:
	 *
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItmeList(Integer page,Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page , rows);
		return result;
	}

	/**
	 * @Date 2018/2/6 16:58
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: 
	 * @Params: 
	 * @ReturnType: 
	 * @Description: 
	 *
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result saveItem(TbItem item, String desc) {
		E3Result e3Result = itemService.addItem(item, desc);
		return e3Result;
	}


}
