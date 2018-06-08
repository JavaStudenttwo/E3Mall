package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Date 2018/6/8 16:23
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.service
 * @ClassName: ItemServiceImplTest
 * @Description:
 *
 */
public class ItemServiceImplTest {

    //@Autowired
    private TbItemMapper itemMapper;
    //@Autowired
    private TbItemDescMapper itemDescMapper;

    //@Test
public void testGetItemList() throws Exception {

        TbItemExample example =  new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);

        System.out.println(list.get(1));


} 


} 
