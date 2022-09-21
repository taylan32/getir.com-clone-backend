package com.example.getirbackend.utils.pagination;

import java.util.List;

import lombok.Getter;

@Getter
public class BasePageableModel<T> {

	private List<T> items;
	private boolean hasPrevious;
	private boolean hasNext;
	private int count;
	private int pageNumber;
	private int pageCount;
	private int pageSize;

	public BasePageableModel() {

	}

	public BasePageableModel(List<T> data, int pageNumber, int pageSize, int dataSize) {

		this.items = data;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.count = dataSize;
		this.pageCount = (int) Math.ceil((double) count / (double) pageSize);
		if (count > pageSize && pageNumber > pageCount) {
			throw new IllegalArgumentException("Geçersiz parametre.");
		}

		setHasPrevious();
		setHasNext();
	}

	private void setHasPrevious() {
		

		if (pageCount <= 1) {
			this.hasPrevious = false;
		} else {
			if (pageNumber == 1) {
				this.hasPrevious = false;
			} else {
				this.hasPrevious = true;
			}
		}

	}

	private void setHasNext() {
		if (pageCount > 1) {
			if (pageNumber != pageCount) {
				this.hasNext = true;
			} else {
				this.hasNext = false;
			}
		} else {
			this.hasNext = false;
		}
	}

}

