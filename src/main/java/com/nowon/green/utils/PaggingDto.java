package com.nowon.green.utils;

import lombok.Getter;

@Getter
public class PaggingDto {

	
	private int to;
	private int from;
	private int tot;
	
	public final static PaggingDto createPagging(int page, int blockSize, int totalPages) {
		return new PaggingDto(page, blockSize, totalPages);
	}
	
	private PaggingDto(int page, int blockSize, int totalPages) {
		int pNo=page/blockSize; //(1~10)0 or 1
		if(page%blockSize>0)pNo++; //(1~10)0->1
		this.to=blockSize * pNo;//10, 20,30
		this.from=to-blockSize+1;//1, 11, 21
		if(to>totalPages)to=totalPages;
		this.tot=totalPages;
	}
}
