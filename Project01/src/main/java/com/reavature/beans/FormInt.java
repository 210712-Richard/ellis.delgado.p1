package com.reavature.beans;

import java.io.Serializable;
import java.util.UUID;

public interface FormInt extends Serializable{
	String getName();
	void setName(String name);
	
	UUID getId();
	void setId(UUID id);
	
	FileType getType();
	void setType(FileType type);
	
	String getUrl();
	void setUrl(String url);

	FileObject getFile();
	void setFile(FileObject file);
}
