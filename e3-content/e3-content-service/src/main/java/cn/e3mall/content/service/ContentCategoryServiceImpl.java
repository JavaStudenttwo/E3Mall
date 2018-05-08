package cn.e3mall.content.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

/**
 * @Date 2018/4/14 23:30
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.content.service
 * @ClassName: ContentCategoryServiceImpl
 * @Description:
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	/**
	 * @Date 2018/4/19 15:38
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: getContentCatList
	 * @Params: [parentId]
	 * @ReturnType: java.util.List<cn.e3mall.common.pojo.EasyUITreeNode>
	 * @Description: 查询商品分类
	 *
	 */
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {

		/** 根据parentid查询子节点列表 */
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		/** 设置查询条件 */
		criteria.andParentIdEqualTo(parentId);
		/** 执行查询 */
		List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);

		List<EasyUITreeNode> nodeList = new ArrayList<>();

		for (TbContentCategory tbContentCategory : catList) {

			EasyUITreeNode node = new EasyUITreeNode();
			node.setText(tbContentCategory.getName());
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			nodeList.add(node);

		}
		return nodeList;
	}

	/**
	 * @Date 2018/4/19 15:42
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: addContentCategory
	 * @Params: [parentId, name]
	 * @ReturnType: cn.e3mall.common.utils.E3Result
	 * @Description: 添加商品分类
	 *
	 */
	@Override
	public E3Result addContentCategory(long parentId, String name) {
		/** 商品分类pojo */
		TbContentCategory contentCategory = new TbContentCategory();

		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());

		/** 添加到数据库 */
		contentCategoryMapper.insert(contentCategory);
		/** 添加商品分类后，原来不是父节点的分类变为父节点，所以遍历所有parentId的分类，将其都改为父节点*/
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}

		return E3Result.ok(contentCategory);
	}

}
