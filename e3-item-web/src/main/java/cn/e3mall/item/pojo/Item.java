package cn.e3mall.item.pojo;

import cn.e3mall.pojo.TbItem;

/**
 * @Date 2018/6/8 22:43
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.item.pojo
 * @ClassName: Item
 * @Description: TbItem增强，添加一个getImages方法
 *
 */
public class Item extends TbItem{

    public String[] getImages() {
        String image2 = this.getImage();
        if (image2 != null && !"".equals(image2)) {
            String[] strings = image2.split(",");
            return strings;
        }
        return null;
    }

    public Item() {
    }

    public Item(TbItem tbItem) {
        this.setBarcode(tbItem.getBarcode());
        this.setCid(tbItem.getCid());
        this.setCreated(tbItem.getCreated());
        this.setId(tbItem.getId());
        this.setImage(tbItem.getImage());
        this.setNum(tbItem.getNum());
        this.setPrice(tbItem.getPrice());
        this.setSellPoint(tbItem.getSellPoint());
        this.setStatus(tbItem.getStatus());
        this.setTitle(tbItem.getTitle());
        this.setUpdated(tbItem.getUpdated());
    }

}
