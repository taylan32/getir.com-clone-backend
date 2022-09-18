package com.example.getirbackend.utils.pagination;

import java.util.List;

public class PaginateHelper<T>{

	private List<T> items;
	private int pageNumber;
	private int pageSize;
	
	public PaginateHelper(List<T> items, int pageNumber, int pageSize) {
		this.items = items;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	
	
	
}
