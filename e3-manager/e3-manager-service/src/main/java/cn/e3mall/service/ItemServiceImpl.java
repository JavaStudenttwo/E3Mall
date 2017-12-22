package cn.e3mall.service;

import java.util.List;

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

	



}
