package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUITreeResult;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
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

    private String state = "open";

    /**
     * @Date 2017/12/21 19:21
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: getTbItemCat
     * @Params: []
     * @ReturnType: cn.e3mall.pojo.TbItemCat
     * @Description: 查询商品种类
     *
     */
    public List<EasyUITreeResult> getTbItemCat(long parentId){

        List<EasyUITreeResult> result = new ArrayList<EasyUITreeResult>();

        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();

        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);

        for (TbItemCat tbItemCat:list ){
            EasyUITreeResult easyUITreeResult = new EasyUITreeResult();
            easyUITreeResult.setId(tbItemCat.getId());
            easyUITreeResult.setText(tbItemCat.getName());

            if (tbItemCat.getIsParent()){
                this.state = "closed";
            }
            easyUITreeResult.setState(state);

            result.add(easyUITreeResult);
        }

        return result;
    }
}
