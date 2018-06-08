package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Date 2018/6/5 21:17
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.solrj
 * @ClassName: TestSolrQuery
 * @Description: solr索引库查询测试
 *
 */
public class TestSolrQuery {

    @Test
    public void queryDocument() throws Exception {
        //创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/webapp/collection1");
        //创建一个查询对象，可以参考solr的后台的查询功能设置条件
        SolrQuery query = new SolrQuery();
        //设置查询条件
//		query.setQuery("阿尔卡特");
        query.set("q", "阿尔卡特");
        //设置分页条件
        query.setStart(1);
        query.setRows(2);
        //开启高亮
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        //设置默认搜索域
        query.set("df", "item_title");
        //执行查询，得到一个QueryResponse对象。
        QueryResponse queryResponse = solrServer.query(query);
        //取查询结果总记录数
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
        //取查询结果
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            //取高亮后的结果
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0) {
                //取高亮后的结果
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
