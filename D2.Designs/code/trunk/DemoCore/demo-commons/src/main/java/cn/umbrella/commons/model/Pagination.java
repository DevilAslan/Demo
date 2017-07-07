package cn.umbrella.commons.model;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable{
	 private static final long serialVersionUID = 1L;
	  public static final String ASC = "asc";
	  public static final String DESC = "desc";
	  private int curPage;
	  private int pageSize;
	  private int rowCount;
	  private String order;
	  private String orderBy;
	  private boolean autoRows = true;
	  private List<T> results;
	  
	  public int getCurPage()
	  {
	    return this.curPage < 1 ? 1 : this.curPage;
	  }
	  
	  public void setCurPage(int curPage)
	  {
	    this.curPage = curPage;
	  }
	  
	  public int getPageSize()
	  {
	    return this.pageSize < 1 ? 1 : this.pageSize;
	  }
	  
	  public void setPageSize(int pageSize)
	  {
	    this.pageSize = pageSize;
	  }
	  
	  public int getRowCount()
	  {
	    return this.rowCount;
	  }
	  
	  public void setRowCount(int rowCount)
	  {
	    this.rowCount = rowCount;
	  }
	  
	  public String getOrder()
	  {
	    return this.order;
	  }
	  
	  public void setOrder(String order)
	  {
	    this.order = order;
	  }
	  
	  public String getOrderBy()
	  {
	    return this.orderBy;
	  }
	  
	  public void setOrderBy(String orderBy)
	  {
	    this.orderBy = orderBy;
	  }
	  
	  public boolean isAutoRows()
	  {
	    return this.autoRows;
	  }
	  
	  public void setAutoRows(boolean autoRows)
	  {
	    this.autoRows = autoRows;
	  }
	  
	  public int getPageCount()
	  {
	    if (getRowCount() < 0) {
	      return -1;
	    }
	    int pageCount = getRowCount() / getPageSize();
	    if (getRowCount() % getPageSize() > 0) {
	      pageCount++;
	    }
	    return pageCount;
	  }
	  
	  public List<T> getResults()
	  {
	    return this.results;
	  }
	  
	  public void setResults(List<T> results)
	  {
	    this.results = results;
	  }
	  
	  public int getFirstPage()
	  {
	    return (getCurPage() - 1) * getPageSize() + 1;
	  }
	  
	  public boolean isHasNext()
	  {
	    return getCurPage() + 1 <= getPageCount();
	  }
	  
	  public int getNextPage()
	  {
	    return isHasNext() ? getCurPage() + 1 : getCurPage();
	  }
	  
	  public boolean isHasPre()
	  {
	    return getCurPage() - 1 >= 1;
	  }
	  
	  public int getPrePage()
	  {
	    return isHasPre() ? getCurPage() - 1 : 1;
	  }
	  
	  public boolean isOrderSetted()
	  {
	    return (StringUtils.isNotBlank(getOrder())) && (StringUtils.isNotBlank(getOrderBy()));
	  }
}
