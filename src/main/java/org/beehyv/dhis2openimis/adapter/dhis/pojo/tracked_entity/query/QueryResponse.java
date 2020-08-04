package org.beehyv.dhis2openimis.adapter.dhis.pojo.tracked_entity.query;

import java.util.List;

public class QueryResponse {
	private List<QueryResponseHeader> headers;
	private Integer width;
	private Integer height;
	private List<List<String>> rows;
	
	public QueryResponse() {
		
	}

	public List<QueryResponseHeader> getHeaders() {
		return headers;
	}
	public void setHeaders(List<QueryResponseHeader> headers) {
		this.headers = headers;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public List<List<String>> getRows() {
		return rows;
	}
	public void setRows(List<List<String>> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "QueryResponse [headers=" + headers + ", width=" + width + ", height=" + height + ", rows=" + rows + "]";
	}
	
}
