package com.hh.model;

import java.util.ArrayList;
import java.util.List;

public class ElementList<T> {
	List<T> list=new ArrayList<T>();

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
