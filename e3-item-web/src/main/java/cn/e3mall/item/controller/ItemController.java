package cn.e3mall.item.controller;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date 2018/6/8 22:54
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.item.controller
 * @ClassName: ItemController
 * @Description:
 *
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * @Date 2018/6/8 22:54
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: showItemInfo
     * @Params: [itemId, model]
     * @ReturnType: java.lang.String
     * @Description:
     *
     */
    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model) {

        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);

//        TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);
//
//        model.addAttribute("item", item);
//        model.addAttribute("itemDesc", tbItemDesc);

        return "item";
    }

}
