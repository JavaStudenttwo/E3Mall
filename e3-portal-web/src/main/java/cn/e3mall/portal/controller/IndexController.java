package cn.e3mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date 2017/12/22 23:53
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.portal.controller
 * @ClassName: IndexController
 * @Description: 页面跳转
 *
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
