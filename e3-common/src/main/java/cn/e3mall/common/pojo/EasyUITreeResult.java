package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * @Date 2017/12/21 16:43
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.pojo
 * @ClassName: EasyUITreeResult
 * @Description: 封装产品分类信息，树数据
 *
 */
public class EasyUITreeResult implements Serializable{

    /**分类id*/
    long id;
    /**分类名称*/
    String text;
    /**是否有子节点的标识，close表示有子节点，open表示没有子节点*/
    String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
