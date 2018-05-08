package cn.e3mall.service;

import java.util.Date;
import java.util.List;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.pojo.TbItemDesc;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.ItemService;

import javax.annotation.Resource;

/** 
 * @Date 2017/12/17 18:52
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.service
 * @ClassName: ItemServiceImpl 
 * @Description: 
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private SolrServer solrServer;

	/**
	 * @Date 2017/12/17 18:52
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: getItemById
	 * @Params: [itemId]
	 * @ReturnType: cn.e3mall.pojo.TbItem
	 * @Description:
	 *
	 */
	@Override
	public TbItem getItemById(long itemId) {

		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();

		criteria.andIdEqualTo(itemId);

		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @Date 2017/12/19 23:01
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: getItemList
	 * @Params: [page, rows]
	 * @ReturnType: cn.e3mall.common.pojo.EasyUIDataGridResult
	 * @Description:
	 *
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {

		PageHelper.startPage(page, rows);
		
		TbItemExample example =  new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		
		PageInfo<TbItem> pageInfo = new PageInfo<> (list);
		
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		return result;
	}

	/**
	 * @Date 2018/2/6 17:06
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: 
	 * @Params: 
	 * @ReturnType: 
	 * @Description: 
	 *
	 */
	@Override
	public E3Result addItem(TbItem item, String desc) {
		/**商品信息初始化*/
		long itemId = IDUtils.getItemId();
		item.setId(itemId);
		/**商品状态：1.正常 2.下架 3.删除*/
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);

		/**商品描述初始化*/
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
		return E3Result.ok();

	}

	@Override
	public E3Result importAllItems() {
//		try {
//			/**从数据库中查出数据*/
//			TbItemExample example =  new TbItemExample();
//			List<TbItem> itemList = itemMapper.selectByExample(example);
//
//
//			/**遍历查出的数据，并将其添加到solr索引库中*/
//			for (TbItem tbItem : itemList) {
//
//				SolrInputDocument document = new SolrInputDocument();
//
//				document.addField("id", tbItem.getId());
//				document.addField("item_title", tbItem.getTitle());
//				document.addField("item_sell_point", "none");
//				document.addField("item_price", tbItem.getPrice());
//				document.addField("item_image", tbItem.getImage());
//				document.addField("item_category_name", "none");
//
//				solrServer.add(document);
//				System.out.println("成功");
//			}
//			solrServer.commit();
//			return E3Result.ok();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("失败");
//			return E3Result.build(500, "数据导入时发生异常");
//
//		}
		return null;
	}


}
