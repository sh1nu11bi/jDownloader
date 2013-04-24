package com.redpois0n;

public class DownloadFile {

	private String url;
	private String filename;
	private String extension;
	private String path;
	private boolean shell;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return filename;
	}

	public void setFileName(String filename) {
		this.filename = filename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isShell() {
		return shell;
	}

	public void setShell(boolean shell) {
		this.shell = shell;
	}

	
}
