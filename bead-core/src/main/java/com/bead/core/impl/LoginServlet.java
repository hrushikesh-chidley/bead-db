package com.bead.core.impl;

import java.io.IOException;

import javax.servlet.ServletException;

public class LoginServlet extends AbstractServlet<LoginRequest> {

	private static final long serialVersionUID = 6738586445341688029L;
	
	@Override
	protected void processPost(Request<LoginRequest> request) throws ServletException, IOException {
		final LoginRequest loginRequest = request.getRequestData();		
		processLogin(loginRequest);		
		request.setProcessed(true);
		request.setProcessingCode(200);
	}

	private void processLogin(final LoginRequest loginRequest) {
		System.out.println(loginRequest);
	}

	@Override
	protected void populateRequest(Request<LoginRequest> request) throws IOException {
		readAndSetRequestData(request, LoginRequest.class);
	}
}
