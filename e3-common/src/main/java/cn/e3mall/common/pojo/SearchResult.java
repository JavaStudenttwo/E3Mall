package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Date 2018/6/5 21:29
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.pojo
 * @ClassName: SearchResult
 * @Description: 搜索结果持久化类
 *
 */
public class SearchResult implements Serializable {

	private long recordCount;
	private int totalPages;
	private List<SearchItem> itemList;

	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	
}
