package com.reavature.beans;

import java.util.UUID;

public class FileObject implements FileInt{

	private static final long serialVersionUID = 1L;
	public String filename;
	public UUID fileId;
	public FileType fileType;
	public String fileUrl;
	
	public FileObject() {
		super();
		
	}
	public FileObject(String filename, FileType fileType, String fileUrl) {
		this();
		this.filename=filename;
		this.fileType= fileType;
		this.fileUrl = fileUrl;
	}
	public String getName() {
		return filename;
	}

	public void setName(String filename) {
		this.filename = filename;
		
	}

	public UUID getId() {
		return fileId;
	}

	public void setId(UUID fileId) {
		this.fileId = fileId;
		
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		result = prime * result + ((fileUrl == null) ? 0 : fileUrl.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		return result;
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
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (fileType != other.fileType)
			return false;
		if (fileUrl == null) {
			if (other.fileUrl != null)
				return false;
		} else if (!fileUrl.equals(other.fileUrl))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FileObject [filename=" + filename + ", fileId=" + fileId + ", fileType=" + fileType + ", fileUrl="
				+ fileUrl + "]";
	}
	

}
