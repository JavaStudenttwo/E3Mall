package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * @Date 2017/12/21 16:43
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.pojo
 * @ClassName: EasyUITreeResult
 * @Description: 封装产品信息树数据
 *
 */
public class EasyUITreeResult implements Serializable{

    long id;
    String text;
    String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
