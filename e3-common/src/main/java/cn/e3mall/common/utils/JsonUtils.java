package cn.e3mall.common.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Date 2018/4/16 10:25
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.utils
 * @ClassName: JsonUtils
 * @Description:
 *
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * @Date 2018/4/16 10:25
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: objectToJson
     * @Params: [data]
     * @ReturnType: java.lang.String
     * @Description: 对象转换为json字符串
     *
     */
    public static String objectToJson(Object data) {

    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }

    /**
     * @Date 2018/4/16 10:25
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: jsonToPojo
     * @Params: [jsonData, beanType]
     * @ReturnType: T
     * @Description: json结果转化为对象
     *
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {

        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * @Date 2018/4/16 10:26
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: jsonToList
     * @Params: [jsonData, beanType]
     * @ReturnType: java.util.List<T>
     * @Description: 将json数据转换成pojo对象list
     *
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {

    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
}
