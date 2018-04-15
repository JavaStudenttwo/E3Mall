package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeResult;
import cn.e3mall.common.utils.E3Result;

public interface ContentCategoryService {

	List<EasyUITreeResult> getContentCatList(long parentId);
	E3Result addContentCategory(long parentId, String name);
}
