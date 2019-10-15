package com.bead.core.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractServlet<T> extends HttpServlet {

	private static final long serialVersionUID = 8211145586559362170L;
	
	private enum HttpMethod{GET, POST, PUT, DELETE};
	
	private ObjectMapper mapper = new ObjectMapper();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp, HttpMethod.GET);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp, HttpMethod.POST);
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp, HttpMethod.PUT);
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp, HttpMethod.DELETE);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp, HttpMethod method) throws ServletException, IOException {
		final AsyncContext context = req.startAsync();
		final ServletOutputStream out = resp.getOutputStream();
		
		final Request<T> request = new Request<T>(req, resp, out);
		
		out.setWriteListener(new WriteListener() {
			@Override
			public void onWritePossible() throws IOException {
				while (out.isReady()) {
					if(request.isProcessed()) {
						resp.setStatus(request.getProcessingCode());
	                    context.complete();
	                    return;
					}
					try {
						processInt(request, method);
					} catch(ServletException | IOException e) {
						onError(e);
					}
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				context.complete();
			}
		});
	}
	
	private void processInt(Request<T> request, HttpMethod method) throws ServletException, IOException {
		if(request.getRequestData() == null) {
			populateRequest(request);
		}
		
		switch(method) {
		case PUT:
			processPut(request);
			break;
		case DELETE:
			processDelete(request);
			break;
		case GET:
			processGet(request);
			break;
		case POST:
			processPost(request);
			break;
		}
	}
	
	protected void readAndSetRequestData(Request<T> request, Class<T> requestType) throws IOException {
		byte[] requestText = new byte[0];
		int availableBytes = -1;
		try (InputStream stream = request.getBaseRequest().getInputStream()) {
			while (availableBytes != 0) {
				availableBytes = stream.available();
				int currentLength = requestText.length;
				byte[] currentBuffer = new byte[availableBytes + currentLength];
				System.arraycopy(requestText, 0, currentBuffer, 0, currentLength);
				stream.read(currentBuffer, currentLength, availableBytes);
				requestText = currentBuffer;
			}
		}
		request.setTypeData(mapper.readValue(requestText, requestType));
	}

	protected void processGet(Request<T> request) throws ServletException, IOException {
		sendMethodNotAllowed(request);
	}

	protected void processPost(Request<T> request) throws ServletException, IOException {
		sendMethodNotAllowed(request);
	}

	protected void processPut(Request<T> request) throws ServletException, IOException {
		sendMethodNotAllowed(request);
	}

	protected void processDelete(Request<T> request) throws ServletException, IOException {
		sendMethodNotAllowed(request);
	}
	
	private void sendMethodNotAllowed(Request<T> request) throws ServletException, IOException {
		request.getBaseResponse().getOutputStream().write("Method-Not-Supported".getBytes("UTF-8"));
		request.setProcessed(true);
		request.setProcessingCode(405);

	}
	
	protected abstract void populateRequest(Request<T> request) throws IOException;
}
