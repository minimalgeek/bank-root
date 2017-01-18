package hu.farago.data.service.dto;

import java.io.Serializable;

public class URLSuccess implements Serializable {

	private static final long serialVersionUID = -2524552494043957105L;

	private String url;
	private boolean success;
	
	public URLSuccess(String url, boolean success) {
		super();
		this.url = url;
		this.success = success;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
