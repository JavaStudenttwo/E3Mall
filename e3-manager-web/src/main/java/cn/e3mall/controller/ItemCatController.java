package cn.e3mall.controller;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Date 2017/12/21 19:08
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.controller
 * @ClassName: ItemCatController
 * @Description: 商品类别
 *
 */
@Controller
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCat(@RequestParam(name = "id",defaultValue = "0")Long parentId){

        List<EasyUITreeNode> list = itemCatService.getTbItemCat(parentId);

        return list;
    }
}
