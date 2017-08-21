package cn.teacheredu.vo;

import java.io.Serializable;
import java.util.Date;

import cn.teacheredu.utils.SystemConst;


public class BaseQueryVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected int curPage = 1;   //当前页面
	
	protected int pageSize = SystemConst.DEFAULT_PAGESIZE;   //每页总条数
	
	protected int startPosition = 0;   //起始数据
	
	protected int totalRows = 0;    //总记录数
	
	protected boolean isPaged = false;  //是否进行分页
	
	protected boolean hasCount = false;
	
	protected String orderProperty; // 暂时只支持一个排序字段
	
	protected String orderType = SystemConst.SQL_ORDERTYPE_DESC;//默认倒序
	
	protected String distinct = null;

	protected Date fromDate;
	
	protected Date toDate;
	
	
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public boolean isPaged() {
		return isPaged;
	}

	public void setPaged(boolean isPaged) {
		this.isPaged = isPaged;
	}

	public boolean isHasCount() {
		return hasCount;
	}

	public void setHasCount(boolean hasCount) {
		this.hasCount = hasCount;
	}

	public String getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDistinct() {
		return distinct;
	}

	public void setDistinct(String distinct) {
		this.distinct = distinct;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
}
