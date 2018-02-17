package cn.e3mall.common.utils;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Date 2018/2/6 17:47
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.utils
 * @ClassName: E3Result
 * @Description: 返回值
 *
 */
public class E3Result implements Serializable{

    /**属性*/
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer status;

    private String msg;

    private Object data;

    /**核心方法*/
    public static E3Result build(Integer status, String msg, Object data) {
        return new E3Result(status, msg, data);
    }

    public static E3Result build(Integer status, String msg) {
        return new E3Result(status, msg, null);
    }

    public static E3Result ok(Object data) {
        return new E3Result(data);
    }

    public static E3Result ok() {
        return new E3Result(null);
    }

    /**构造方法*/
    public E3Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public E3Result(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    /**
     * @Date 2018/2/6 17:49
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: formatToPojo
     * @Params: [jsonData, clazz]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description:
     *
     */
    public static E3Result formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, E3Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Date 2018/2/6 17:49
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: format
     * @Params: [json]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description:
     *
     */
    public static E3Result format(String json) {
        try {
            return MAPPER.readValue(json, E3Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Date 2018/2/6 17:49
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: formatToList
     * @Params: [jsonData, clazz]
     * @ReturnType: cn.e3mall.common.utils.E3Result
     * @Description:
     *
     */
    public static E3Result formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
