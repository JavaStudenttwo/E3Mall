package cn.e3mall.cart;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Value("${TT_CART}")
    private String TT_CART;
    @Value("${CART_EXPIRE}")
    private Integer CART_EXPIRE;

    @Autowired
    private ItemService itemService;

    /**
     * @Date 2018/6/9 23:35
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: addCartItem
     * @Params: [itemId, num, request, response]
     * @ReturnType: java.lang.String
     * @Description:
     *
     */
    @RequestMapping("/cart/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, Integer num,
                              HttpServletRequest request, HttpServletResponse response) {

        // 1、从cookie中查询商品列表。
        List<TbItem> cartList = getCartList(request);
        // 2、判断商品在商品列表中是否存在。
        boolean hasItem = false;
        for (TbItem tbItem : cartList) {
            //对象比较的是地址，应该是值的比较
            if (tbItem.getId() == itemId.longValue()) {
                // 3、如果存在，商品数量相加。
                tbItem.setNum(tbItem.getNum() + num);
                hasItem = true;
                break;
            }
        }
        if (!hasItem) {
            // 4、不存在，根据商品id查询商品信息。
            TbItem tbItem = itemService.getItemById(itemId);
            //取一张图片
            String image = tbItem.getImage();
            if (StringUtils.isNoneBlank(image)) {
                String[] images = image.split(",");
                tbItem.setImage(images[0]);
            }
            //设置购买商品数量
            tbItem.setNum(num);
            // 5、把商品添加到购车列表。
            cartList.add(tbItem);
        }
        // 6、把购车商品列表写入cookie。
        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(cartList), CART_EXPIRE, true);
        return "cartSuccess";
    }

    /**
     * @Date 2018/6/9 23:35
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: getCartList
     * @Params: [request]
     * @ReturnType: java.util.List<cn.e3mall.pojo.TbItem>
     * @Description:
     *
     */
    private List<TbItem> getCartList(HttpServletRequest request) {

        //取购物车列表
        String json = CookieUtils.getCookieValue(request, TT_CART, true);
        //判断json是否为null
        if (StringUtils.isNotBlank(json)) {
            //把json转换成商品列表返回
            List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
            return list;
        }
        return new ArrayList<>();
    }

    /**
     * @Date 2018/6/9 23:36
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: showCartList
     * @Params: [request, model]
     * @ReturnType: java.lang.String
     * @Description:
     *
     */
    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request, Model model) {

        List<TbItem> cartList = getCartList(request);
        model.addAttribute("cartList", cartList);
        return "cart";

    }

    /**
     * @Date 2018/6/9 23:46
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: updateNum
     * @Params: [itemId, num, request, response]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description:
     *
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateNum(@PathVariable Long itemId, @PathVariable Integer num,
                              HttpServletRequest request, HttpServletResponse response) {
        // 1、接收两个参数
        // 2、从cookie中取商品列表
        List<TbItem> cartList = getCartList(request);
        // 3、遍历商品列表找到对应商品
        for (TbItem tbItem : cartList) {
            if (tbItem.getId() == itemId.longValue()) {
                // 4、更新商品数量
                tbItem.setNum(num);
            }
        }
        // 5、把商品列表写入cookie。
        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(cartList), CART_EXPIRE, true);
        // 6、响应e3Result。Json数据。
        return E3Result.ok();

    }

    /**
     * @Date 2018/6/9 23:47
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: deleteCartItem
     * @Params: [itemId, request, response]
     * @ReturnType: java.lang.String
     * @Description:
     *
     */
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
                                 HttpServletResponse response) {
        // 1、从url中取商品id
        // 2、从cookie中取购物车商品列表
        List<TbItem> cartList = getCartList(request);
        // 3、遍历列表找到对应的商品
        for (TbItem tbItem : cartList) {
            if (tbItem.getId() == itemId.longValue()) {
                // 4、删除商品。
                cartList.remove(tbItem);
                break;
            }
        }
        // 5、把商品列表写入cookie。
        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(cartList), CART_EXPIRE, true);
        // 6、返回逻辑视图：在逻辑视图中做redirect跳转。
        return "redirect:/cart/cart.html";
    }





}
