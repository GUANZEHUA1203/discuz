package com.util;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONException;
import lombok.Setter;
import sun.security.jca.GetInstance;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 *
 * @author peak
 */
public class PageUtils<T> implements Serializable {

	private static final long serialVersionUID = 3297445200287541862L;

	public final static String PARAMS_KEY_PAGE_NO = "page";

	public final static String PARAMS_KEY_PAGE_LIMIT = "rows";

	public final static String PARAMS_KEY_PAGE_OFFSET = "offset";

	public final static String PARAMS_KEY_VO_SEARCH_MODE = "vo";

	public final static int DEFAULT_PAGE_SIZE = 20;
	public final static int DEFAULT_PAGE = 1;

	// 一页显示的记录数
	/** The limit. */
	private int limit = DEFAULT_PAGE_SIZE;
	// 记录总数
	/** The total rows. */
	private long totalRows;
	// 当前页码
	/** The page no. */
	private int pageNo = DEFAULT_PAGE;
	// 结果集存放List
	/** The result list. */
	private List<T> resultList;
	@Setter
	private T query;

	public PageUtils(int limit, long totalRows, int pageNo, List<T> resultList) {
		super();
		this.limit = (limit == 0 ? DEFAULT_PAGE_SIZE : limit);
		this.totalRows = totalRows;
		this.pageNo = (pageNo == 0 ? DEFAULT_PAGE : pageNo);
		this.resultList = resultList;
	}

	public PageUtils() {
	}

	public static <T> PageUtils<T> getInstance(int pageNo,int limit,T query){
		PageUtils pageUtils = new PageUtils();
		pageUtils.pageNo = pageNo;
		pageUtils.limit = limit;
		pageUtils.query = query;
		return pageUtils;
	}

	/**
	 * Gets the limit.
	 *
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets the limit.
	 *
	 * @param limit
	 *            the new limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Gets the result list.
	 *
	 * @return the result list
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * Sets the result list.
	 *
	 * @param resultList
	 *            the new result list
	 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	// 计算总页数
	/**
	 * Gets the total pages.
	 *
	 * @return the total pages
	 */
	public long getTotalPages() {
		long totalPages;
		if (totalRows % limit == 0) {
			totalPages = totalRows / limit;
		} else {
			totalPages = (totalRows / limit) + 1;
		}
		return totalPages;
	}

	/**
	 * Gets the total rows.
	 *
	 * @return the total rows
	 */
	public long getTotalRows() {
		return totalRows;
	}

	/**
	 * Sets the total rows.
	 *
	 * @param totalRows
	 *            the new total rows
	 */
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public int getOffset() {
		return (pageNo - 1) * limit;
	}

	/**
	 * Gets the end index.
	 *
	 * @return the end index
	 */
	public long getEndIndex() {
		if (getOffset() + limit > totalRows) {
			return totalRows;
		} else {
			return getOffset() + limit;
		}
	}

	/**
	 * Gets the page no.
	 *
	 * @return the page no
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * Sets the page no.
	 *
	 * @param pageNo
	 *            the new page no
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public boolean hasNextPage() {
		boolean hasNextPage = false;
		if ((pageNo * limit) < totalRows) {
			hasNextPage = true;
		}
		return hasNextPage;
	}

	public String toJSONString() {
		try {
			String resultArray = FastJSONUtil.object2json(resultList);
			return "{\"total\":" + totalRows + ",\"rows\":" + resultArray + "}";
		} catch (JSONException e) {
			e.printStackTrace();
			return "{\"total\":0,\"rows\":[]}";
		}

	}
}
