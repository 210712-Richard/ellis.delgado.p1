package com.reavature.beans;

import java.io.Serializable;
import java.util.UUID;

public interface FileInt extends Serializable{


	FileType getType();
	void setType(FileType type);
	
	String getUrl();
	void setUrl(String url);
}	
