package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * @Date  22:59
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.pojo
 * @ClassName: EasyUIDataGridResult
 * @Description: 封装分页结果集
 *
 */
public class EasyUIDataGridResult implements Serializable{

	/**总记录数*/
	private long total;
	/**结果集对象，List集合*/
	private List rows;

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	

	
}
