package com.revature.beans;

import java.util.Objects;


public class FileObject implements FileInt{

	private static final long serialVersionUID = 1L;
	
	public FileType fileType;
	public String fileUrl;
	
	public FileObject() {
		super();
		
	}
	public FileObject(FileType fileType, String fileUrl) {
		this();
		this.fileType= fileType;
		this.fileUrl = fileUrl;
	}
	
	public FileType getType() {
		return fileType;
	}

	public void setType(FileType fileType) {
		this.fileType = fileType;
		
	}

	public String getUrl() {
		return fileUrl;
	}

	public void setUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(fileType, fileUrl);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileObject other = (FileObject) obj;
		return fileType == other.fileType && Objects.equals(fileUrl, other.fileUrl);
	}
	@Override
	public String toString() {
		return "FileObject [fileType=" + fileType + ", fileUrl=" + fileUrl + "]";
	}
	
	

}
