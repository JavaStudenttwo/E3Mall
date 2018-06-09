package cn.e3mall.sso.controller;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @Date 2018/6/9 16:19
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.sso.controller
 * @ClassName: UserController 
 * @Description: 
 *
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Date 2018/6/9 16:20
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: checkData
     * @Params: [param, type]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description: 用户信息检查
     *
     */
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public E3Result checkData(@PathVariable String param, @PathVariable Integer type) {

        E3Result e3Result = userService.checkData(param, type);
        return e3Result;

    }

    /**
     * @Date 2018/6/9 21:07
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: register
     * @Params: [user]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description: 用户注册
     *
     */
    @RequestMapping(value="/user/register", method= RequestMethod.POST)
    @ResponseBody
    public E3Result register(TbUser user) {

        E3Result result = userService.createUser(user);
        return result;

    }

    /**
     * @Date 2018/6/9 21:20
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: getUserByToken
     * @Params: [token]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description: 从缓存中查询用户
     *
     */
    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public E3Result getUserByToken(@PathVariable String token) {

        E3Result result = userService.getUserByToken(token);
        return result;

    }



}
