package cn.umbrella.commons.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Aslan
 */
public class PageModel<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<T> rows;

    private Integer page;

    private Integer count;

    public PageModel() {

    }

    public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public PageModel(Collection<T> rows, Integer page, Integer count) {
        this.rows = rows;
        this.page = page;
        this.count = count;
    }
}
