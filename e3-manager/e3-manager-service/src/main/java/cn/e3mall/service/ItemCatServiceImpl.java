package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2017/12/21 19:21
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.service
 * @ClassName: ItemCatServiceImpl
 * @Description: 处理和商品种类相关的业务
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    TbItemCatMapper tbItemCatMapper;

    /**
     * @Date 2017/12/21 19:21
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: getTbItemCat
     * @Params: []
     * @ReturnType: cn.e3mall.pojo.TbItemCat
     * @Description: 查询商品种类
     *
     */
    public List<EasyUITreeNode> getTbItemCat(long parentId){

        List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();

        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();

        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);

        for (TbItemCat tbItemCat : list) {

            EasyUITreeNode node = new EasyUITreeNode();
            node.setText(tbItemCat.getName());
            node.setId(tbItemCat.getId());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            result.add(node);

        }

        return result;
    }
}
