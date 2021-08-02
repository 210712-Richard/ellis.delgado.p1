package com.reavature.beans;

import java.util.UUID;

public class FileObject implements FileInt{

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
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String filename) {
		// TODO Auto-generated method stub
		
	}

	public UUID getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(UUID fileId) {
		// TODO Auto-generated method stub
		
	}

	public FileType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setType(FileType fileType) {
		// TODO Auto-generated method stub
		
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUrl(String fileUrl) {
		// TODO Auto-generated method stub
		
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
