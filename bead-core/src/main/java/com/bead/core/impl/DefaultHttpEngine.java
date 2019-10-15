package com.bead.core.impl;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import com.bead.common.Throttlings;
import com.bead.core.ProtocolBasedEngine;

public class DefaultHttpEngine implements ProtocolBasedEngine {

	@Override
	public void start() throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        ServerConnector sslConnector = new ServerConnector(server, new SslContextFactory());
        connector.setPort(8090);
        sslConnector.setPort(8097);
        server.setConnectors(new Connector[] {connector, sslConnector});
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(LoginServlet.class, "/login");
        server.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void throttle(Throttlings throttling) {
		// TODO Auto-generated method stub
		
	}

}
