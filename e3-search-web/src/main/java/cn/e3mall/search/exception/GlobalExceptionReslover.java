package cn.e3mall.search.exception;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date 2018/6/8 16:15
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.search.controller
 * @ClassName: GlobalExceptionReslover
 * @Description: 全局异常处理器
 *
 */
public class GlobalExceptionReslover implements HandlerExceptionResolver{

    Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);

    /**
     * @Date 2018/6/8 16:16
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: resolveException
     * @Params: [request, response, handler, ex]
     * @ReturnType: org.springframework.web.servlet.ModelAndView
     * @Description: 处理方法
     *
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        logger.error("系统发生异常", ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "系统发生异常，请稍后重试");
        modelAndView.setViewName("error/exception");
        return modelAndView;

    }
}
