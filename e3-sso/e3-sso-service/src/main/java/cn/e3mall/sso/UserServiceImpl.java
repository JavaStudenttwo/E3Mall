package cn.e3mall.sso;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Date 2018/6/9 20:16
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.sso.service
 * @ClassName: UserServiceImpl
 * @Description:
 *
 */
public class UserServiceImpl implements UserService{

    @Autowired
    TbUserMapper userMapper;
    @Autowired
    JedisClient jedisClient;

    @Value("${USER_INFO}")
    private String USER_INFO;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    /**
     * @Date 2018/6/9 16:01
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: checkData
     * @Params: [param, type]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description: 验证用户输入的信息是否和数据库中已存在的信息冲突，即检查输入信息是否可用
     *               由于存在多种类型的信息，包括电话，邮箱等...所以使用一个type作为参数标识一下到底检查什么类型的信息
     *
     */
    @Override
    public E3Result checkData(String param, int type) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        /**查询参数*/
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return E3Result.build(400, "非法的参数");
        }

        List<TbUser> list = userMapper.selectByExample(example);

        if (list == null || list.size() == 0) {
            return E3Result.ok(true);
        }

        return E3Result.ok(false);
    }

    /**
     * @Date 2018/6/9 21:06
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: createUser
     * @Params: [user]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description: 创建用户
     *
     */
    @Override
    public E3Result createUser(TbUser user) {

        if (StringUtils.isBlank(user.getUsername())) {
            return E3Result.build(400, "用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return E3Result.build(400, "密码不能为空");
        }
        E3Result result = checkData(user.getUsername(), 1);
        if (!(boolean) result.getData()) {
            return E3Result.build(400, "此用户名已经被使用");
        }
        if (StringUtils.isNotBlank(user.getPhone())) {
            result = checkData(user.getPhone(), 2);
            if (!(boolean) result.getData()) {
                return E3Result.build(400, "此手机号已经被使用");
            }
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            result = checkData(user.getEmail(), 3);
            if (!(boolean) result.getData()) {
                return E3Result.build(400, "此邮件地址已经被使用");
            }
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        userMapper.insert(user);
        return E3Result.ok();
    }

    /**
     * @Date 2018/6/9 21:13
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: login
     * @Params: [username, password]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description: 用户登录方法
     *
     */
    @Override
    public E3Result login(String username, String password) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return E3Result.build(400, "用户名或密码错误");
        }
        TbUser user = list.get(0);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return E3Result.build(400, "用户名或密码错误");
        }
        String token = UUID.randomUUID().toString();
        user.setPassword(null);
        jedisClient.set(USER_INFO + ":" + token, JsonUtils.objectToJson(user));
        jedisClient.expire(USER_INFO + ":" + token, SESSION_EXPIRE);
        return E3Result.ok(token);

    }

    /**
     * @Date 2018/6/9 21:19
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: getUserByToken
     * @Params: [token]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description:
     *
     */
    @Override
    public E3Result getUserByToken(String token) {

        String json = jedisClient.get(USER_INFO + ":" + token);
        if (StringUtils.isBlank(json)) {
            return E3Result.build(400, "用户登录已经过期，请重新登录。");
        }
        jedisClient.expire(USER_INFO + ":" + token, SESSION_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        return E3Result.ok(user);

    }



}
