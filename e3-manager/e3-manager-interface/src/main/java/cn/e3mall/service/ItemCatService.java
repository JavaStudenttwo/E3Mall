package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUITreeResult;

import java.util.List;

public interface ItemCatService {

    List<EasyUITreeResult> getTbItemCat(long parentId);
}
