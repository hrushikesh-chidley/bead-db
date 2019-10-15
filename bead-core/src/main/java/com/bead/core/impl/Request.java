package com.bead.core.impl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class Request<T> {

	private T typeData;
	private boolean isProcessed = false;
	private int processingCode;
	
	private final HttpServletRequest baseRequest;
	private final HttpServletResponse baseResponse;
	private final ServletOutputStream out;
	
	public Request(HttpServletRequest baseRequest, HttpServletResponse baseResponse, ServletOutputStream out) {
		this.baseRequest = baseRequest;
		this.baseResponse = baseResponse;
		this.out = out;
	}
	
	public final T getRequestData() {
		return typeData;
	}
	public final HttpServletRequest getBaseRequest() {
		return baseRequest;
	}
	public final HttpServletResponse getBaseResponse() {
		return baseResponse;
	}
	public final ServletOutputStream getOut() {
		return out;
	}
	public final boolean isProcessed() {
		return isProcessed;
	}
	public final int getProcessingCode() {
		return processingCode;
	}
	public final void setTypeData(T typeData) {
		this.typeData = typeData;
	}
	public final void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	public final void setProcessingCode(int processingCode) {
		this.processingCode = processingCode;
	}
}
