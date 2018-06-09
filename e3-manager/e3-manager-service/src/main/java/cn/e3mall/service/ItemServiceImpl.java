package cn.e3mall.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import cn.e3mall.common.jedis.JedisClient;
import com.sun.imageio.plugins.common.I18N;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;

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
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination topicDestination;
	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_INFO_PRE}")
	private String ITEM_INFO_PRE;
	@Value("${ITEM_INFO_EXPIRE}")
	private Integer ITEM_INFO_EXPIRE;

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

		/**先从缓存中查找数据*/
		try {
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + itemId + ":BASE");
			if (StringUtils.isNotBlank(json)) {
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();

		criteria.andIdEqualTo(itemId);

		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {

			/**将查询出的数据添加到缓存*/
			try {
				jedisClient.set(ITEM_INFO_PRE + ":" + itemId + ":BASE", JsonUtils.objectToJson(list.get(0)));
				jedisClient.expire(ITEM_INFO_PRE + ":" + itemId + ":BASE", ITEM_INFO_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
		final long itemId = IDUtils.getItemId();
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

		/**添加商品后给search工程发送消息*/
		jmsTemplate.send(topicDestination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(itemId + "");
				return textMessage;
			}
		});

		return E3Result.ok();

	}

	/**
	 * @Date 2018/6/8 22:48
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: getItemDescById
	 * @Params: [itemId]
	 * @ReturnType: cn.e3mall.pojo.TbItemDesc
	 * @Description: item-web工程调用的方法
	 *
	 */
	@Override
	public TbItemDesc getItemDescById(Long itemId) {

		try {
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + itemId + ":DESC");
			if (StringUtils.isNotBlank(json) ) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return itemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

		try {
			jedisClient.set(ITEM_INFO_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
			jedisClient.expire(ITEM_INFO_PRE + ":" + itemId + ":DESC", ITEM_INFO_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemDesc;

	}


}
