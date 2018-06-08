package cn.e3mall.solrj;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class TestItemMapper {

    @Resource(name = "itemMapper")
    private ItemMapper itemMapper;

    @Test
    public void testAdd(){
        List<SearchItem> itemList = itemMapper.getItemList();
        SearchItem searchItem = itemList.get(1);
        System.out.println(searchItem);
    }
}
