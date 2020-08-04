package org.beehyv.dhis2openimis.adapter.openimis.pojo;

public class LinkDetail {
	private String relation;
	private String url;
	
	public LinkDetail() {
		
	}
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "LinkDetail [relation=" + relation + ", url=" + url + "]";
	}
	
	
}
