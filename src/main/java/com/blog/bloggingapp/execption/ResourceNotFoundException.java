package com.blog.bloggingapp.execption;

public class ResourceNotFoundException extends RuntimeException{
	
	
	String resourceName;
	String fieldName;
	long feildValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long feildValue) 
	{
		super(String.format("%s not found with %s:%s",resourceName,fieldName,feildValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.feildValue = feildValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getFeildValue() {
		return feildValue;
	}

	public void setFeildValue(long feildValue) {
		this.feildValue = feildValue;
	}
	
	
	

}
