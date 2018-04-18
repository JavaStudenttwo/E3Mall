package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Date 2018/4/17 10:50
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: java.cn.e3mall.solrj
 * @ClassName: TestSolrJ
 * @Description: solr索引库操作测试方法
 *
 */
public class TestSolrJ {

	/**
	 * @Date 2018/4/17 10:51
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: addDocument
	 * @Params: []
	 * @ReturnType: void
	 * @Description: 向solr索引库中添加索引文档
	 *
	 */
	@Test
	public void addDocument() throws Exception {

		/**创建一个SolrServer对象，创建一个连接。参数solr服务的url*/
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/webapp/collection1");

		/**创建一个文档对象SolrInputDocument*/
		SolrInputDocument document = new SolrInputDocument();

		/**向文档对象中添加域。文档中必须包含一个id域，所有的域的名称必须在schema.xml中定义。*/
		document.addField("id", "doc01");
		document.addField("item_title", "测试商品01");
		document.addField("item_price", 1000);

		/**把文档写入索引库*/
		solrServer.add(document);
		/**提交*/
		solrServer.commit();
	}

	/**
	 * @Date 2018/4/17 10:58
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: deleteDocument
	 * @Params: []
	 * @ReturnType: void
	 * @Description: 删除文档
	 *
	 */
	@Test
	public void deleteDocument() throws Exception {

		SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/webapp/collection1");

		solrServer.deleteByQuery("id:doc01");

		solrServer.commit();
	}

	/**
	 * @Date 2018/4/17 11:00
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: queryIndex
	 * @Params: []
	 * @ReturnType: void
	 * @Description: 查询索引库
	 *
	 */
	@Test
	public void queryIndex() throws Exception {

		/**创建一个SolrServer对象*/
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/webapp/collection1");
		/**创建一个SolrQuery对象*/
		SolrQuery query = new SolrQuery();
		/**设置查询条件。*/
		//query.setQuery("*:*");
		query.set("q", "*:*");
		/**执行查询，QueryResponse对象*/
		QueryResponse queryResponse = solrServer.query(query);
		/**取文档列表，取查询结果的总记录数*/
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		/**遍历文档列表*/
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}
	
	@Test
	public void queryIndexFuza() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/webapp/collection1");

		SolrQuery query = new SolrQuery();

		query.setQuery("手机");
		query.setStart(0);
		query.setRows(20);
		query.set("df", "item_title");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		//执行查询
		QueryResponse queryResponse = solrServer.query(query);
		//取文档列表。取查询结果的总记录数
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		//遍历文档列表，从取域的内容。
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			//取高亮显示
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list !=null && list.size() > 0 ) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			System.out.println(title);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}
	
}
