package cn.e3mall.content.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * @Date 2018/4/17 23:09
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: addContent
	 * @Params: [content]
	 * @ReturnType: cn.e3mall.common.utils.E3Result
	 * @Description:
	 *
	 */
	@Override
	public E3Result addContent(TbContent content) {
		
		//将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入到数据库
		contentMapper.insert(content);
		return E3Result.ok();
	}

	/**
	 * @Date 2018/4/17 23:09
	 * @Author CycloneKid sk18810356@gmail.com 
	 * @MethodName: getContentListByCid
	 * @Params: [cid]
	 * @ReturnType: java.util.List<cn.e3mall.pojo.TbContent>
	 * @Description:
	 *
	 */
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		return list;
	}

}
