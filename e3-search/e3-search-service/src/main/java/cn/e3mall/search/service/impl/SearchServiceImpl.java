package cn.e3mall.search.service.impl;

import cn.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;

/** 
 * @Date 2018/4/16 10:18
 * @Author CycloneKid sk18810356@gmail.com 
 * @PackageName: cn.e3mall.search.service.impl
 * @ClassName: SearchServiceImpl 
 * @Description: 
 *
 */
@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	/**
	 * @Date 2018/4/16 11:14
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: search
	 * @Params: [keyword, page, rows]
	 * @ReturnType: cn.e3mall.common.pojo.SearchResult
	 * @Description:
	 *
	 */
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {

		SolrQuery query = new SolrQuery();
		/**设置查询条件*/
		query.setQuery(keyword);
		/**设置分页条件*/
		if (page <=0 ) page =1;
		query.setStart((page - 1) * rows);
		query.setRows(rows);

		/**设置默认搜索域*/
		query.set("df", "item_title");

		/**高亮显示*/
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");

		/**查询数据库*/
		SearchResult searchResult = searchDao.search(query);

		/**计算查询结果页数*/
		long recordCount = searchResult.getRecordCount();
		int totalPage = (int) (recordCount / rows);
		if (recordCount % rows > 0) 
			totalPage ++;
		searchResult.setTotalPages(totalPage);

		return searchResult;
	}

}
