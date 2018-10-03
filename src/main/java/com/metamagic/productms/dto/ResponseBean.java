package com.metamagic.productms.dto;

import java.util.Date;

public class ResponseBean {

	private String version;
	private Date responsedate;
	private Object response;
	
	
	
	public ResponseBean(String version, Object response) {
		super();
		this.version = version;
		this.responsedate = new Date();
		this.response = response;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getResponsedate() {
		return responsedate;
	}
	public void setResponsedate(Date responsedate) {
		this.responsedate = responsedate;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	
}
