package cn.e3mall.common.utils;

import java.util.Random;

/**
 * @Date 2018/2/6 17:10
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.common.utils
 * @ClassName: IDUtils 
 * @Description: 
 *
 */
public class IDUtils {

    /**
     * @Date 2018/2/6 17:53
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: genImageName
     * @Params: []
     * @ReturnType: java.lang.String
     * @Description: 图片id生成方法
     *
     */
    public static String getImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);
        /**位数不够时补零*/
        String str = millis + String.format("%03d", end3);
        return str;
    }

    /**
     * @Date 2018/2/6 17:53
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: genItemId
     * @Params: []
     * @ReturnType: long
     * @Description: 商品id生成方法
     *
     */
    public static long getItemId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(99);
        /**位数不够时补零*/
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

}
