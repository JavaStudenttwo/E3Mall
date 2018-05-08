package cn.e3mall.content.service;

import java.util.Date;
import java.util.List;

import cn.e3mall.common.jedis.JedisClientCluster;
import cn.e3mall.common.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

/** 
 * @Date 2018/4/17 23:14
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.content.service
 * @ClassName: ContentServiceImpl 
 * @Description: 
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClientCluster jedisClientCluster;
	@Value("${CONTENT_LIST}")
	private String CONTENT_KEY;


	/**
	 * @Date 2018/4/17 23:09
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: addContent
	 * @Params: [content]
	 * @ReturnType: cn.e3mall.common.utils.E3Result
	 * @Description: 添加商品内容
	 *
	 */
	@Override
	public E3Result addContent(TbContent content) {
		/** 添加创建日期和最近修改日期*/
		content.setCreated(new Date());
		content.setUpdated(new Date());

		contentMapper.insert(content);
		return E3Result.ok();
	}

	/**
	 * @Date 2018/4/17 23:09
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: getContentListByCid
	 * @Params: [cid]
	 * @ReturnType: java.util.List<cn.e3mall.pojo.TbContent>
	 * @Description: 根据商品内容分类查询商品内容
	 *
	 */
	@Override
	public List<TbContent> getContentListByCid(long cid) {

		/**先查询缓存，缓存中有数据就直接取出来*/
		try {
			String json = jedisClientCluster.hget(CONTENT_KEY, cid + "");
			if (StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			/**捕获异常，将异常写入日志，不往外抛*/
			e.printStackTrace();
		}

		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		/** 使用商品分类cid查询 */
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

		/**在缓存中没有数据的情况下，查询数据库后将查出的数据存入缓存中*/
		try {
			jedisClientCluster.hset(CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
