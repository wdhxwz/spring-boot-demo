package com.spring.boot.demo.models;

import java.io.Serializable;

public abstract class BaseModel<T> implements Serializable{
	private static final long serialVersionUID = -8113945968392389426L;

	private T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}	
}